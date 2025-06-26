'use client';

import Link from 'next/link';
import {
    User,
    Home,
    LayoutDashboard,
    UserPlus,
    ChevronRight,
    Save,
    ChevronUp,
    ChevronDown
} from 'lucide-react';
import { useState } from 'react';
import InputTextField from '@/components/TextField/InputTextField';
import SimpleTextField from '@/components/TextField/SimpleTextField';
import DatePickerInput from '@/components/DatePicker/DatePicker';

export default function CadastroPaciente() {
    const [nomeCompleto, setNomeCompleto] = useState('');
    const [dataNascimento, setDataNascimento] = useState('2025-04-22');
    const [mae, setMae] = useState('');
    const [pai, setPai] = useState('');
    const [outro, setOutro] = useState('');
    const [cpf, setCpf] = useState('');
    const [rg, setRg] = useState('');
    const [cartaoSus, setCartaoSus] = useState('');
    const [celular, setCelular] = useState('');
    const [cadastroAberto, setCadastroAberto] = useState(true);

    return (
        <div className="flex flex-col min-h-screen text-main bg-background">
            <div className="flex flex-1">
                <aside
                    className="w-64 bg-darkgray p-6 overflow-y-auto"
                    style={{ boxShadow: '4px 0 8px rgba(0, 0, 0, 0.2)' }}
                >
                    <h2 className="text-xl font-bold mb-6 flex items-center gap-2 text-main">
                        <Home size={18} /> <span>Menu</span>
                    </h2>

                    <nav className="flex flex-col gap-4 text-sm">
                        <Link
                            href="/dashboard"
                            className="flex items-center gap-2 p-2 rounded border border-transparent text-main hover:border-greendark hover:bg-button hover:text-greendark transition"
                        >
                            <LayoutDashboard size={18} /> <span>Dashboard</span>
                        </Link>
                        <Link
                            href="/listagem-pacientes"
                            className="flex items-center gap-2 p-2 rounded border border-transparent text-main hover:border-greendark hover:bg-button hover:text-greendark transition"
                        >
                            <User size={18} /> <span>Pacientes</span>
                        </Link>

                        <div>
                            <button
                                onClick={() => setCadastroAberto(!cadastroAberto)}
                                className="w-full flex items-center justify-between p-2 rounded border border-transparent text-main hover:border-greendark hover:bg-button hover:text-greendark transition"
                            >
                                <span className="flex items-center gap-2">
                                    <UserPlus size={18} /> Cadastro
                                </span>
                                {cadastroAberto ? <ChevronUp size={16} /> : <ChevronDown size={16} />}
                            </button>

                            {cadastroAberto && (
                                <ul className="mt-2 flex flex-col gap-3">
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
                                        <li
                                            key={idx}
                                            className="rounded border border-transparent text-main hover:border-greendark hover:bg-button hover:text-greendark transition"
                                        >
                                            <Link href={href} className="block px-2 py-1 hover:text-greendark">
                                                {label}
                                            </Link>
                                        </li>
                                    ))}
                                </ul>
                            )}
                        </div>
                    </nav>
                </aside>

                <main className="flex-1 p-6 mt-4 overflow-auto">
                    <div className="max-w-10xl mx-auto flex flex-col">
                        <h1 className="flex items-center gap-2 text-2xl font-bold mb-6 text-center">
                            Cadastro <UserPlus size={18} />
                        </h1>

                        <div className="bg-offwhite rounded-lg shadow max-h-[calc(88vh-200px)] flex flex-col">
                            <div className="p-6 border-b border-graymedium bg-offwhite sticky top-0 z-10">
                                <h2 className="text-xl text-darkgray font-semibold mb-2">1. Identificação do Paciente</h2>
                                <h3 className="text-darkgray">Informações pessoais e de contato do paciente:</h3>
                            </div>

                            <div className="overflow-y-auto p-6 space-y-6 pr-2 flex-1">
                                <div className="bg-offwhite p-6 rounded-lg shadow">
                                    <SimpleTextField
                                        nomeCampo="Nome Completo"
                                        placeholder="Nome Completo"
                                        valorPreenchido={nomeCompleto}
                                        onChange={setNomeCompleto}
                                        className="w-full"
                                    />
                                </div>

                                <div className="bg-offwhite p-6 rounded-lg shadow">
                                    <DatePickerInput
                                        title="Data de Nascimento"
                                        value={dataNascimento}
                                        onChange={setDataNascimento}
                                        className="w-full"
                                    />
                                </div>

                                <div className="bg-offwhite p-6 rounded-lg shadow space-y-4">
                                    <SimpleTextField
                                        nomeCampo="Mãe"
                                        placeholder="Nome da mãe"
                                        valorPreenchido={mae}
                                        onChange={setMae}
                                        className="w-full"
                                    />
                                    <SimpleTextField
                                        nomeCampo="Pai"
                                        placeholder="Nome do pai"
                                        valorPreenchido={pai}
                                        onChange={setPai}
                                        className="w-full"
                                    />
                                    <SimpleTextField
                                        nomeCampo="Outro Responsável"
                                        placeholder="Digite o nome do responsável"
                                        valorPreenchido={outro}
                                        onChange={setOutro}
                                        className="w-full"
                                    />
                                </div>

                                <div className="bg-offwhite p-6 rounded-lg shadow">
                                    <div className="flex gap-6">
                                        <div className="w-1/2">
                                            <InputTextField
                                                nomeCampo="CPF"
                                                valorPreenchido={cpf}
                                                onChange={setCpf}
                                                somenteNumero
                                                className="w-full"
                                            />
                                        </div>
                                        <div className="w-1/2">
                                            <InputTextField
                                                nomeCampo="RG"
                                                valorPreenchido={rg}
                                                onChange={setRg}
                                                somenteNumero
                                                className="w-full"
                                            />
                                        </div>
                                    </div>
                                </div>

                                <div className="bg-offwhite p-6 rounded-lg shadow">
                                    <div className="flex gap-6">
                                        <div className="w-1/2">
                                            <InputTextField
                                                nomeCampo="Cartão do SUS"
                                                valorPreenchido={cartaoSus}
                                                onChange={setCartaoSus}
                                                somenteNumero
                                                className="w-full"
                                            />
                                        </div>
                                        <div className="w-1/2">
                                            <InputTextField
                                                nomeCampo="Celular"
                                                valorPreenchido={celular}
                                                onChange={setCelular}
                                                somenteNumero
                                                className="w-full"
                                            />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div className="flex items-center mt-3 pt-6 border-t border-graymedium">
                            <div className="flex-grow flex justify-center">
                                <button className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green text-sm transition">
                                    <Save size={18} /> Salvar
                                </button>
                            </div>
                            <Link
                                href="/cadastro/socio-economico"
                                className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green transition text-sm"
                            >
                                Próximo <ChevronRight size={18} />
                            </Link>
                        </div>
                    </div>
                </main>
            </div>

            {/* Footer */}
            <footer className="w-full mt-auto text-center text-xs text-main bg-darkgray p-6 shadow-[4px_0_8px_rgba(0,0,0,0.2)]">
                © {new Date().getFullYear()} Sistema de Gestão de Pacientes - Todos os direitos reservados.
            </footer>
        </div>
    );
}
