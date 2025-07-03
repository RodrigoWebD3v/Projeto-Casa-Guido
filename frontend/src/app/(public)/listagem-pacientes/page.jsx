'use client';

import Link from 'next/link';
import { useEffect, useState } from 'react';
import { Eye, Edit, Trash, Users, UserPlus, Home, LayoutDashboard } from 'lucide-react';
import { pacientes as pacientesMock } from './pacientes'; // corrigido o nome aqui

function limparCpf(cpf) {
  if (typeof cpf !== 'string') return '';
  return cpf.replace(/[.\-]/g, '');
}

export default function ListaPacientes() {
  const [pacientes, setPacientes] = useState([]);
  const [searchNome, setSearchNome] = useState('');
  const [searchCpf, setSearchCpf] = useState('');
  const [termoNomeFiltrado, setTermoNomeFiltrado] = useState('');
  const [termoCpfFiltrado, setTermoCpfFiltrado] = useState('');
  const [loading, setLoading] = useState(true);
  const [erro, setErro] = useState('');

  useEffect(() => {
    try {
      setPacientes(pacientesMock); // usando a lista importada corretamente
      setLoading(false);
    } catch (err) {
      setErro('Erro ao carregar pacientes');
      setLoading(false);
    }
  }, []);

  const pacientesFiltrados = pacientes
    .filter((paciente) => {
      const nomePaciente = paciente.nome.toLowerCase();
      const termoNome = termoNomeFiltrado.toLowerCase();

      const cpfPaciente = limparCpf(paciente.cpf);
      const termoCpf = limparCpf(termoCpfFiltrado);

      const nomeValido = termoNome === '' || nomePaciente.startsWith(termoNome);
      const cpfValido = termoCpf === '' || cpfPaciente.startsWith(termoCpf);

      return nomeValido && cpfValido;
    })
    .slice()
    .sort((a, b) => a.nome.localeCompare(b.nome));

  const handleBuscar = (e) => {
    e.preventDefault();
    setTermoNomeFiltrado(searchNome);
    setTermoCpfFiltrado(searchCpf);
  };

  return (
    <div className="flex flex-col min-h-screen bg-background text-main">
      <div className="flex flex-1">
        <aside className="w-44 bg-sidebarbg p-6 shadow-[4px_0_8px_rgba(0,0,0,0.2)]">
          <h2 className="text-xl font-bold mb-6 flex items-center gap-2 text-sidebaricon">
            <Home size={18} /> <span>Menu</span>
          </h2>
          <nav className="flex flex-col gap-4">
            <Link
              href="/listagem-pacientes"
              className="flex items-center gap-2 p-2 rounded border border-transparent text-sidebartext hover:border-greendark hover:bg-sidebarhoverbg hover:text-success transition"
            >
              <LayoutDashboard size={18} className="text-sidebaricon" /> <span>Dashboard</span>
            </Link>
            <Link
              href="/cadastro/identificacao-paciente"
              className="flex items-center gap-2 p-2 rounded border border-transparent text-sidebartext hover:border-greendark hover:bg-sidebarhoverbg hover:text-success transition"
            >
              <UserPlus size={18} className="text-sidebaricon" /> <span>Cadastrar</span>
            </Link>
          </nav>
        </aside>

        <main className="flex-1 p-6 mt-4 overflow-auto">
          <h1 className="flex items-center gap-2 text-2xl font-bold mb-6 text-center">
            Lista de Pacientes <Users size={24} />
          </h1>

          <form onSubmit={handleBuscar} className="mb-6 max-w-md flex gap-2">
            <input
              type="text"
              placeholder="Buscar por nome"
              value={searchNome}
              onChange={(e) => {
                const apenasLetras = e.target.value.replace(/[0-9]/g, '');
                setSearchNome(apenasLetras);
              }}
              className="flex-1 p-2 border rounded text-blacktext"
            />
            <input
              type="text"
              placeholder="Buscar por CPF"
              value={searchCpf}
              onChange={(e) => {
                let valor = e.target.value.replace(/\D/g, '').slice(0, 11);
                if (valor.length > 9) {
                  valor = valor.replace(/(\d{3})(\d{3})(\d{3})(\d{1,2})/, '$1.$2.$3-$4');
                } else if (valor.length > 6) {
                  valor = valor.replace(/(\d{3})(\d{3})(\d{1,3})/, '$1.$2.$3');
                } else if (valor.length > 3) {
                  valor = valor.replace(/(\d{3})(\d{1,3})/, '$1.$2');
                }
                setSearchCpf(valor);
              }}
              maxLength={14}
              className="flex-1 p-2 border rounded text-blacktext"
            />
            <button
              type="submit"
              className="bg-button text-offwhite px-4 py-2 rounded hover:bg-success transition"
            >
              Buscar
            </button>
          </form>

          {loading && <p className="text-blacktext mb-4">Carregando pacientes...</p>}
          {erro && <p className="text-red-600 mb-4">{erro}</p>}

          <div className="overflow-x-auto max-h-[700px] rounded shadow relative">
            <table className="min-w-full bg-offwhite">
              <thead className="bg-success">
                <tr className="text-left text-sm uppercase tracking-wider text-greendark">
                  <th className="sticky top-0 bg-success z-30 p-4 border-l border-r border-darkgray shadow-sm">Nome</th>
                  <th className="sticky top-0 bg-success z-30 p-4 border-r border-darkgray shadow-sm">CPF</th>
                  <th className="sticky top-0 bg-success z-30 p-4 border-r border-darkgray shadow-sm">Idade</th>
                  <th className="sticky top-0 bg-success z-30 p-4 border-r border-darkgray shadow-sm">Telefone</th>
                  <th className="sticky top-0 bg-success z-30 p-4 border-r border-darkgray text-center shadow-sm">Ações</th>
                </tr>
              </thead>
              <tbody>
                {pacientesFiltrados.length > 0 ? (
                  pacientesFiltrados.map((paciente) => (
                    <tr key={paciente.id} className="border-b hover:bg-success/40 transition text-greendark">
                      <td className="p-4 border-l border-r border-darkgray">{paciente.nome}</td>
                      <td className="p-4 border-r border-darkgray">{paciente.cpf}</td>
                      <td className="p-4 border-r border-darkgray">{paciente.idade}</td>
                      <td className="p-4 border-r border-darkgray">{paciente.telefone}</td>
                      <td className="p-4 border-r border-darkgray">
                        <div className="flex justify-center gap-4">
                          <button className="text-blue-600 hover:text-blue-800" title="Visualizar paciente">
                            <Eye size={18} />
                          </button>
                          <button className="text-yellow-600 hover:text-yellow-800" title="Editar paciente">
                            <Edit size={18} />
                          </button>
                          <button className="text-red-600 hover:text-red-800" title="Excluir paciente">
                            <Trash size={18} />
                          </button>
                        </div>
                      </td>
                    </tr>
                  ))
                ) : (
                  <tr>
                    <td colSpan={5} className="text-center p-4 text-blacktext">
                      Nenhum paciente encontrado.
                    </td>
                  </tr>
                )}
              </tbody>
            </table>
          </div>
        </main>
      </div>

      <footer className="w-full mt-auto text-center text-xs text-sidebartext bg-sidebarbg p-6 shadow-[4px_0_8px_rgba(0,0,0,0.2)]">
        © {new Date().getFullYear()} Sistema de Gestão de Pacientes - Todos os direitos reservados.
      </footer>
    </div>
  );
}
