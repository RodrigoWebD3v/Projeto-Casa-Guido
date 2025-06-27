// components/Sidebar.tsx
'use client';

import { useState } from 'react';
import Link from 'next/link';
import {
    Home,
    LayoutDashboard,
    User,
    UserPlus,
    ChevronUp,
    ChevronDown,
} from 'lucide-react';

export default function Sidebar() {
    const [cadastroAberto, setCadastroAberto] = useState(false);

    return (
        <aside
            className={`bg-sidebarbg p-6 overflow-y-auto transition-all duration-300 ease-in-out ${cadastroAberto ? 'w-64' : 'w-44'
                }`}
            style={{ boxShadow: '4px 0 8px rgba(0, 0, 0, 0.2)' }}
        >
            <h2 className="text-xl font-bold mb-6 flex items-center gap-2 text-sidebaricon">
                <Home size={18} /> <span>Menu</span>
            </h2>

            <nav className="flex flex-col gap-4 text-sm">
                <Link
                    href="/dashboard"
                    className="flex items-center gap-2 p-2 rounded border border-transparent text-sidebartext hover:border-sidebarhoverbg hover:bg-sidebarhoverbg hover:text-success transition"
                >
                    <LayoutDashboard className='text-sidebaricon' size={18} /> <span>Dashboard</span>
                </Link>
                <Link
                    href="/listagem-pacientes"
                    className="flex items-center gap-2 p-2 rounded border border-transparent text-sidebartext hover:border-sidebarhoverbg hover:bg-sidebarhoverbg hover:text-success transition"
                >
                    <User size={18} className='text-sidebaricon' /> <span>Pacientes</span>
                </Link>

                <div>
                    <button
                        onClick={() => setCadastroAberto(!cadastroAberto)}
                        className="w-full flex items-center justify-between p-2 rounded border border-transparent text-main hover:border-sidebarhoverbg hover:bg-sidebarhoverbg hover:text-success transition"
                    >
                        <span className="flex items-center gap-2">
                            <UserPlus size={18} className='text-sidebaricon' /> Cadastro
                        </span>
                        {cadastroAberto ? <ChevronUp size={16} /> : <ChevronDown size={16} />}
                    </button>

                    {cadastroAberto && (
                        <ul className="mt-2 flex flex-col gap-3 whitespace-nowrap">
                            {[
                                ['1 - Identificação do Paciente', '/cadastro/identificacao-paciente'],
                                ['2 - Socioeconômico', '/cadastro/socio-economico'],
                                ['3 - Cirurgias', '/cadastro/cirurgias'],
                                ['4 - Quimioterapia', '/cadastro/quimio'],
                                ['5 - Radioterapia', '/cadastro/radio'],
                                ['6 - Responsável', '/cadastro/responsavel'],
                                ['7 - Responsável (Opcional)', '/cadastro/responsavel-opcional'],
                                ['8 - Composição Familiar', '/cadastro/composicao-familiar'],
                                ['9 - Histórico de Saúde', '/cadastro/historico-saude'],
                                ['10 - Situação Habitacional', '/cadastro/situacao-habitacional'],
                                ['11 - Endereço', '/cadastro/endereco'],
                                ['12 - Demais Dados', '/cadastro/demais-dados'],
                            ].map(([label, href], idx) => (
                                <li key={idx}>
                                    <Link
                                        href={href}
                                        className="block px-4 py-2 rounded-lg border shadow-lg hover:shadow-lg border-sidebarhoverbg text-sidebartext hover:border-sidebarhoverbg hover:bg-sidebarhoverbg hover:text-success transition duration-200 ease-in-out cursor-pointer select-none"
                                    >
                                        {label}
                                    </Link>
                                </li>
                            ))}
                        </ul>
                    )}
                </div>
            </nav>
        </aside>
    );
}
