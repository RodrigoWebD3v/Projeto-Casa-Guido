'use client';

import Link from 'next/link';
import { useState } from 'react';
import { Eye, Edit, Trash, Users, UserPlus, Home, LayoutDashboard } from 'lucide-react';
import { pacientes } from './pacientes';

export default function ListaPacientes() {
    const [searchNome, setSearchNome] = useState('');
    const [searchCpf, setSearchCpf] = useState('');
    const [termoNomeFiltrado, setTermoNomeFiltrado] = useState('');
    const [termoCpfFiltrado, setTermoCpfFiltrado] = useState('');

    const pacientesFiltrados = pacientes.filter((paciente) =>
        paciente.nome.toLowerCase().includes(termoNomeFiltrado.toLowerCase()) &&
        paciente.cpf.includes(termoCpfFiltrado)
    );

    const handleBuscar = (e) => {
        e.preventDefault();
        setTermoNomeFiltrado(searchNome);
        setTermoCpfFiltrado(searchCpf);
    };

    return (
        <div className="flex min-h-screen bg-background text-main">
            {/* Menu lateral */}
            <aside
                className="w-44 bg-gray-700 p-6"
                style={{ boxShadow: '4px 0 8px rgba(0, 0, 0, 0.2)' }}
            >
                <h2 className="text-xl font-bold mb-6 flex items-center gap-2 text-white">
                    <Home size={18} /> <span>Menu</span>
                </h2>
                <nav className="flex flex-col gap-4">
                    <Link
                        href="/dashboard"
                        className="flex items-center gap-2 p-2 rounded border border-transparent hover:border-white hover:bg-gray-400 hover:text-green-600 transition text-white"
                    >
                        <LayoutDashboard size={18} /> <span>Dashboard</span>
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
                <h1 className="flex items-center gap-3 text-2xl font-bold mb-4 text-main">
                    Lista de Pacientes <Users size={24} />
                </h1>

                {/* Formulário de busca */}
                <form onSubmit={handleBuscar} className="mb-6 max-w-md flex gap-2">
                    <input
                        type="text"
                        placeholder="Buscar por nome"
                        value={searchNome}
                        onChange={(e) => setSearchNome(e.target.value)}
                        className="flex-1 p-2 border rounded text-black"
                    />
                    <input
                        type="text"
                        placeholder="Buscar por CPF"
                        value={searchCpf}
                        onChange={(e) => setSearchCpf(e.target.value)}
                        className="flex-1 p-2 border rounded text-black"
                    />
                    <button
                        type="submit"
                        className="bg-green-600 text-white px-4 py-2 rounded hover:bg-green-800 transition"
                    >
                        Buscar
                    </button>
                </form>

                {/* Tabela de pacientes */}
                <div className="overflow-x-auto max-h-[730px] rounded shadow">
                    <table className="min-w-full bg-white rounded">
                        <thead>
                            <tr className="bg-gray-200 text-left text-sm uppercase tracking-wider text-black">
                                <th className="p-4 border-r border-gray-300">Nome</th>
                                <th className="p-4 border-r border-gray-300">CPF</th>
                                <th className="p-4 border-r border-gray-300">Idade</th>
                                <th className="p-4 border-r border-gray-300">Telefone</th>
                                <th className="p-4 border-r border-gray-300 text-center">Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            {pacientesFiltrados.length > 0 ? (
                                pacientesFiltrados.map((paciente) => (
                                    <tr
                                        key={paciente.id}
                                        className="border-b hover:bg-gray-100 transition text-black"
                                    >
                                        <td className="p-4 border-r border-gray-300">{paciente.nome}</td>
                                        <td className="p-4 border-r border-gray-300">{paciente.cpf}</td>
                                        <td className="p-4 border-r border-gray-300">{paciente.idade}</td>
                                        <td className="p-4 border-r border-gray-300">{paciente.telefone}</td>
                                        <td className="p-4 border-r border-gray-300">
                                            <div className="flex justify-center gap-4">
                                                <button
                                                    className="text-blue-600 hover:text-blue-800"
                                                    title="Visualizar paciente"
                                                >
                                                    <Eye size={18} />
                                                </button>
                                                <button
                                                    className="text-yellow-600 hover:text-yellow-800"
                                                    title="Editar paciente"
                                                >
                                                    <Edit size={18} />
                                                </button>
                                                <button
                                                    className="text-red-600 hover:text-red-800"
                                                    title="Excluir paciente"
                                                >
                                                    <Trash size={18} />
                                                </button>
                                            </div>
                                        </td>
                                    </tr>
                                ))
                            ) : (
                                <tr>
                                    <td colSpan={5} className="text-center p-4 text-gray-500">
                                        Nenhum paciente encontrado.
                                    </td>
                                </tr>
                            )}
                        </tbody>
                    </table>
                </div>
            </main>
        </div>
    );
}
