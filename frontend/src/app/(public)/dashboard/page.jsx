'use client';

import Link from 'next/link';
import { Home, UserPlus, User, LayoutDashboard } from 'lucide-react';
import { agendamentos } from './agendamentos';
import { useState, useEffect } from 'react';

function calcularStats(agendamentos) {
  const hoje = new Date();
  const hojeStr = hoje.toISOString().split('T')[0];

  const ontem = new Date(hoje);
  ontem.setDate(hoje.getDate() - 1);
  const ontemStr = ontem.toISOString().split('T')[0];

  const seteDiasAtras = new Date();
  seteDiasAtras.setDate(hoje.getDate() - 6);

  const semanaAnteriorInicio = new Date(seteDiasAtras);
  semanaAnteriorInicio.setDate(seteDiasAtras.getDate() - 7);
  const semanaAnteriorFim = new Date(seteDiasAtras);
  semanaAnteriorFim.setDate(seteDiasAtras.getDate() - 1);

  const agendamentosHoje = agendamentos.filter(a => a.data === hojeStr);
  const agendamentosOntem = agendamentos.filter(a => a.data === ontemStr);

  const agendamentosSemana = agendamentos.filter(a => {
    const data = new Date(a.data);
    return data >= seteDiasAtras && data <= hoje;
  });

  const agendamentosSemanaAnterior = agendamentos.filter(a => {
    const data = new Date(a.data);
    return data >= semanaAnteriorInicio && data <= semanaAnteriorFim;
  });

  const totalPacientes = new Set(agendamentos.map(a => a.nome)).size;
  const hojeCount = agendamentosHoje.length;
  const ontemCount = agendamentosOntem.length;
  const semanaCount = agendamentosSemana.length;
  const semanaAnteriorCount = agendamentosSemanaAnterior.length;

  const confirmadosHoje = agendamentosHoje.filter(a => a.status === 'CONFIRMADO').length;

  const comparecimento = hojeCount > 0
    ? `${Math.round((confirmadosHoje / hojeCount) * 100)}%`
    : '0%';

  const getDiffPercent = (hoje, ontem) => {
    if (ontem === 0) return hoje > 0 ? 'Sem dados' : '—';
    const diff = Math.round(((hoje - ontem) / ontem) * 100);
    return `${diff >= 0 ? '+' : ''}${diff}%`;
  };

  return [
    {
      title: 'Total de Pacientes',
      value: totalPacientes,
      description: getDiffPercent(totalPacientes, totalPacientes - 5) + ' este mês',
    },
    {
      title: 'Hoje',
      value: hojeCount,
      description: getDiffPercent(hojeCount, ontemCount) + ' em relação a ontem',
    },
    {
      title: 'Semana',
      value: semanaCount,
      description: getDiffPercent(semanaCount, semanaAnteriorCount) + ' vs anterior',
    },
    {
      title: 'Comparecimento',
      value: comparecimento,
      description: getDiffPercent(confirmadosHoje, 5) + ' este mês',
    },
  ];
}

export default function Dashboard() {
  const [stats, setStats] = useState(null);

  useEffect(() => {
    setStats(calcularStats(agendamentos));
  }, []);

  const getStatusColor = (status) => {
    if (status === 'CONFIRMADO') return 'bg-green-600';
    if (status === 'CANCELADO') return 'bg-red-600';
    return 'bg-yellow-500';
  };

  const getDescriptionColor = (description) => {
    if (description.startsWith('+')) return 'text-green-600';
    if (description.startsWith('-')) return 'text-red-600';
    return 'text-gray-400';
  };

  const getIniciais = (nome) => {
    return nome
      .split(' ')
      .filter((n) => n)
      .slice(0, 2)
      .map((n) => n[0].toUpperCase())
      .join('');
  };

  if (!stats) {
    return (
      <div className="flex min-h-screen items-center justify-center bg-background text-main">
        <p>Carregando...</p>
      </div>
    );
  }

  return (
    <div className="flex min-h-screen bg-background text-main">
      {/* Sidebar */}
      <aside
        className="w-44 bg-gray-700 p-6"
        style={{ boxShadow: '4px 0 8px rgba(0, 0, 0, 0.2)' }}
      >
        <h2 className="text-xl font-bold mb-6 flex items-center gap-2 text-white">
          <Home size={18} /> <span>Menu</span>
        </h2>
        <nav className="flex flex-col gap-4">
          <Link
            href="/listagem-pacientes"
            className="flex items-center gap-2 p-2 rounded border border-transparent hover:border-white hover:bg-gray-400 hover:text-green-600 transition text-white"
          >
            <User size={18} /> <span>Pacientes</span>
          </Link>
          <Link
            href="#"
            className="flex items-center gap-2 p-2 rounded border border-transparent hover:border-white hover:bg-gray-400 hover:text-green-600 transition text-white"
          >
            <UserPlus size={18} /> <span>Cadastrar</span>
          </Link>
        </nav>
      </aside>

      {/* Conteúdo principal */}
      <main className="flex-1 p-6">
        <h1 className="flex items-center gap-2 text-2xl font-bold mb-6 text-center">
          <LayoutDashboard size={20} />
          Dashboard
        </h1>

        {/* Cards de estatísticas */}
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-4 mb-8">
          {stats.map((stat, idx) => (
            <div key={idx} className="bg-white p-4 rounded-lg shadow-md">
              <p className="text-sm text-black">{stat.title}</p>
              <p className="text-2xl font-bold text-gray-500">{stat.value}</p>
              <p className={`text-sm ${getDescriptionColor(stat.description)}`}>
                {stat.description}
              </p>
            </div>
          ))}
        </div>

        {/* Caixa de título dos agendamentos */}
        <p className="text-md text-main font-semibold">Agendamentos para hoje:</p>
        <div className="space-y-3 max-h-[550px] overflow-y-auto">
          {agendamentos
            .filter(item => item.data === new Date().toISOString().split('T')[0])
            .map((item, idx) => (
              <div
                key={idx}
                className="flex items-center justify-between bg-white p-4 rounded-lg shadow"
              >
                <div className="flex items-center gap-4">
                  <div className="w-10 h-10 rounded-full bg-green-200 text-green-800 flex items-center justify-center font-bold uppercase">
                    {getIniciais(item.nome)}
                  </div>
                  <div>
                    <p className="font-semibold">{item.nome}</p>
                    <p className="text-sm text-gray-500">
                      {item.hora} — {item.tipo}
                    </p>
                  </div>
                </div>
                <span
                  className={`text-white px-3 py-1 rounded-full text-sm ${getStatusColor(item.status)}`}
                >
                  {item.status}
                </span>
              </div>
            ))
          }
        </div>

        {agendamentos.filter(item => item.data === new Date().toISOString().split('T')[0]).length === 0 && (
          <p className="text-center text-gray-500 mt-4">Nenhum agendamento para hoje</p>
        )}
      </main>
    </div>
  );
}
