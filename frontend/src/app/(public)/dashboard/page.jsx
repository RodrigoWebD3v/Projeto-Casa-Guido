'use client';

import { useState } from 'react';
import { Home, UserPlus, Calendar, User } from 'lucide-react';
const stats = [
  { title: 'Total de Pacientes', value: 248, description: '+12% este mês' },
  { title: 'Hoje', value: 12, description: '+4% em relação a ontem' },
  { title: 'Semana', value: 42, description: '+8% vs anterior' },
  { title: 'Comparecimento', value: '92%', description: '-2% este mês' },
];

const agendamentos = [
  { nome: 'Ana Silva', hora: '08:00', tipo: 'Consulta inicial', status: 'CONFIRMADO' },
  { nome: 'Carlos Oliveira', hora: '09:00', tipo: 'Retorno', status: 'PENDENTE' },
  { nome: 'Mariana Santos', hora: '10:00', tipo: 'Exame', status: 'CONFIRMADO' },
  { nome: 'Pedro Costa', hora: '10:00', tipo: 'Procedimento', status: 'CONFIRMADO' },
  { nome: 'Juliana Lima', hora: '10:00', tipo: 'Exame', status: 'PENDENTE' },
  { nome: 'Mariana Santos', hora: '10:00', tipo: 'Consulta inicial', status: 'CONFIRMADO' },
  { nome: 'Pedro Costa', hora: '10:00', tipo: 'Exame', status: 'CONFIRMADO' },
  { nome: 'Pedro Costa', hora: '10:00', tipo: 'Exame', status: 'CONFIRMADO' },
  { nome: 'Pedro Costa', hora: '10:00', tipo: 'Exame', status: 'PENDENTE' },
  { nome: 'Pedro Costa', hora: '10:00', tipo: 'Exame', status: 'CONFIRMADO' }
];

export default function Dashboard() {
  const getStatusColor = (status) =>
    status === 'CONFIRMADO' ? 'bg-green-600' : 'bg-yellow-500';

  const getDescriptionColor = (description) => {
    if (description.startsWith('+')) return 'text-green-600';
    if (description.startsWith('-')) return 'text-red-600';
    return 'text-gray-400';
  };

  return (
    <div className="flex min-h-screen text-main">
      {/* Sidebar */}
      <aside className="w-44 bg-gray-700 shadow-sm p-6">
        <h2 className="text-xl font-bold mb-6 flex items-center gap-2"><Home size={20} /> Menu </h2>
        <nav className="flex flex-col gap-4">
          <a href="#" className="flex items-center gap-2 p-2 rounded border border-transparent hover:border-white hover:bg-gray-400 hover:text-green-600 transition"><UserPlus size={18} /> Cadastrar</a>
          <a href="#" className="flex items-center gap-2 p-2 rounded border border-transparent hover:border-white hover:bg-gray-400 hover:text-green-600 transition"> <User size={18} /> Pacientes </a>
        </nav>
      </aside>

      {/* Conteúdo principal */}
      <main className="flex-1 p-6 bg-background">
        <h1 className="text-2xl font-bold mb-6 text-center">Dashboard</h1>

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


        {/* Lista com scroll */}
        <div className="flex flex-col gap-4 max-h-[550px] overflow-y-auto text-black">
          {agendamentos.map((item, idx) => (
            <div
              key={idx}
              className="flex items-center justify-between bg-white p-4 rounded-lg shadow"
            >
              <div className="flex items-center gap-4">
                <div className="w-10 h-10 rounded-full bg-green-200 text-green-800 flex items-center justify-center font-bold uppercase">
                  {item.nome.split(' ').map((n) => n[0]).join('')}
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
          ))}
        </div>
      </main>
    </div>
  );
}
