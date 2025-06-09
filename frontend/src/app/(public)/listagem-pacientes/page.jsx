'use client';

import Link from 'next/link';
import { useState } from 'react';
import { Eye, Edit, Trash, Users, UserPlus, Home, LayoutDashboard } from 'lucide-react';
import SearchBox from '@/components/SearchBox/SearchBox';

const pacientes = [
    { id: 1, nome: 'Ana Silva', idade: 29, telefone: '(11) 91234-5678' },
    { id: 2, nome: 'Carlos Oliveira', idade: 45, telefone: '(21) 99876-5432' },
    { id: 3, nome: 'Juliana Lima', idade: 34, telefone: '(31) 98765-4321' },
    { id: 4, nome: 'Mariana Santos', idade: 22, telefone: '(41) 92345-6789' },
    { id: 5, nome: 'Pedro Costa', idade: 38, telefone: '(51) 93456-7890' },
    { id: 6, nome: 'Roberta Almeida', idade: 27, telefone: '(61) 94567-8901' },
    { id: 7, nome: 'Fernanda Rocha', idade: 31, telefone: '(71) 95678-9012' },
    { id: 8, nome: 'Ricardo Martins', idade: 40, telefone: '(81) 96789-0123' },
    { id: 9, nome: 'Tatiane Souza', idade: 26, telefone: '(91) 97890-1234' },
    { id: 10, nome: 'Lucas Pereira', idade: 33, telefone: '(85) 98901-2345' },
    { id: 11, nome: 'Gabriel Fernandes', idade: 30, telefone: '(61) 99012-3456' }
];

export default function ListaPacientes() {
    const [search, setSearch] = useState('');

    const pacientesFiltrados = pacientes.filter((paciente) =>
        paciente.nome.toLowerCase().includes(search.toLowerCase())
    );

    return (
        <div className="flex min-h-screen bg-background text-main">
            {/* Menu lateral */}
            <aside className="w-44 bg-gray-700 shadow-sm p-6">
                <h2 className="text-xl font-bold mb-6 flex items-center gap-2">
                    <Home size={20} /> Menu
                </h2>
                <nav className="flex flex-col gap-4">
                    <Link
                        href="/dashboard"
                        className="flex items-center gap-2 p-2 rounded border border-transparent hover:border-white hover:bg-gray-400 hover:text-green-600 transition"
                    >
                        <LayoutDashboard size={18} /> Dashboard
                    </Link>
                    <Link
                        href="#"
                        className="flex items-center gap-2 p-2 rounded border border-transparent hover:border-white hover:bg-gray-400 hover:text-green-600 transition"
                    >
                        <UserPlus size={18} /> Cadastrar
                    </Link>
                </nav>
            </aside>

            {/* Conteúdo principal */}
            <main className="flex-1 p-6">
                <h1 className="flex items-center gap-3 text-2xl font-bold mb-4 text-main">
                    Lista de Pacientes <Users size={24} />
                </h1>

                <div className="mb-6 max-w-md">
                    <SearchBox value={search} onValueChange={setSearch} />
                </div>

                <div className="overflow-x-auto rounded shadow">
                    <table className="min-w-full bg-white rounded">
                        <thead>
                            <tr className="bg-gray-200 text-left text-sm uppercase tracking-wider text-black">
                                <th className="p-4">Nome</th>
                                <th className="p-4">Idade</th>
                                <th className="p-4">Telefone</th>
                                <th className="p-4 text-center">Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            {pacientesFiltrados.length > 0 ? (
                                pacientesFiltrados.map((paciente) => (
                                    <tr
                                        key={paciente.id}
                                        className="border-b hover:bg-gray-100 transition text-black"
                                    >
                                        <td className="p-4">{paciente.nome}</td>
                                        <td className="p-4">{paciente.idade}</td>
                                        <td className="p-4">{paciente.telefone}</td>
                                        <td className="p-4 text-center flex justify-center gap-4">
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
                                        </td>
                                    </tr>
                                ))
                            ) : (
                                <tr>
                                    <td colSpan={4} className="text-center p-4 text-gray-500">
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
