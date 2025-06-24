'use client';

import Link from 'next/link';
import { User, Home, LayoutDashboard, UserPlus, ChevronLeft, ChevronRight, Save } from 'lucide-react';
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

    return (
        <div className="flex min-h-screen text-main bg-background">
            <aside
                className="w-44 bg-darkgray p-6 "
                style={{ boxShadow: '4px 0 8px rgba(0, 0, 0, 0.2)' }}
            >
                <h2 className="text-xl font-bold mb-6 flex items-center gap-2 text-main">
                    <Home size={18} /> <span>Menu</span>
                </h2>
                <nav className="flex flex-col gap-4">
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
                </nav>
            </aside>

            <main className="flex-1 p-6 mt-4">
                <div className="max-w-10xl mx-auto">
                    <h1 className="flex items-center gap-2 text-2xl font-bold mb-6 text-center">
                        Cadastro <UserPlus size={18} />
                    </h1>

                    <div className="bg-offwhite p-6 rounded-lg shadow">
                        <h2 className="text-xl text-darkgray font-semibold mb-4">1. Identificação do Paciente</h2>
                        <h3 className="text-darkgray mb-8">Informações pessoais e de contato do paciente</h3>

                        <div className="space-y-6">
                            <div className="bg-offwhite p-6 rounded-lg shadow">
                                <SimpleTextField
                                    nomeCampo="Nome Completo"
                                    placeholder="Nome Completo"
                                    valorPreenchido={nomeCompleto}
                                    onChange={setNomeCompleto}
                                    className="bg-graylight border border-graymedium rounded-md p-2 transition focus:outline-none focus:ring-2 focus:ring-greenstrong w-full"
                                />
                            </div>

                            <div className="bg-offwhite p-6 rounded-lg shadow">
                                <DatePickerInput
                                    title="Data de Nascimento"
                                    value={dataNascimento}
                                    onChange={setDataNascimento}
                                    className="bg-graylight border border-graymedium rounded-md p-2 transition focus:outline-none focus:ring-2 focus:ring-greenstrong w-full"
                                />
                            </div>

                            <div className="bg-offwhite p-6 rounded-lg shadow space-y-4">
                                <SimpleTextField
                                    nomeCampo="Mãe"
                                    placeholder="Nome da mãe"
                                    valorPreenchido={mae}
                                    onChange={setMae}
                                    className="bg-graylight border border-graymedium rounded-md p-2 transition focus:outline-none focus:ring-2 focus:ring-greenstrong w-full"
                                />

                                <SimpleTextField
                                    nomeCampo="Pai"
                                    placeholder="Nome do pai"
                                    valorPreenchido={pai}
                                    onChange={setPai}
                                    className="bg-graylight border border-graymedium rounded-md p-2 transition focus:outline-none focus:ring-2 focus:ring-greenstrong w-full"
                                />

                                <SimpleTextField
                                    nomeCampo="Outro Responsável"
                                    placeholder="Digite o nome do responsável"
                                    valorPreenchido={outro}
                                    onChange={setOutro}
                                    className="bg-graylight border border-graymedium rounded-md p-2 transition focus:outline-none focus:ring-2 focus:ring-greenstrong w-full"
                                />
                            </div>

                            <div className="bg-offwhite p-6 rounded-lg shadow">
                                <div className="flex gap-6">
                                    <div className="w-1/2">
                                        <InputTextField
                                            nomeCampo="CPF"
                                            valorPreenchido={cpf}
                                            onChange={setCpf}
                                            somenteNumero={true}
                                            className="bg-graylight border border-graymedium rounded-md p-2 transition focus:outline-none focus:ring-2 focus:ring-greenstrong w-full"
                                        />
                                    </div>

                                    <div className="w-1/2">
                                        <InputTextField
                                            nomeCampo="RG"
                                            valorPreenchido={rg}
                                            onChange={setRg}
                                            somenteNumero={true}
                                            className="bg-graylight border border-graymedium rounded-md p-2 transition focus:outline-none focus:ring-2 focus:ring-greenstrong w-full"
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
                                            somenteNumero={true}
                                            className="bg-graylight border border-graymedium rounded-md p-2 transition focus:outline-none focus:ring-2 focus:ring-greenstrong w-full"
                                        />
                                    </div>

                                    <div className="w-1/2">
                                        <InputTextField
                                            nomeCampo="Celular"
                                            valorPreenchido={celular}
                                            onChange={setCelular}
                                            somenteNumero={true}
                                            className="bg-graylight border border-graymedium rounded-md p-2 transition focus:outline-none focus:ring-2 focus:ring-greenstrong w-full"
                                        />
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div className="flex items-center mt-8 pt-6 border-t border-graymedium">
                            <button className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green text-sm transition">
                                <ChevronLeft size={18} /> Anterior
                            </button>

                            <div className="flex-grow flex justify-center">
                                <button className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green text-sm transition">
                                    <Save size={18} /> Salvar
                                </button>
                            </div>

                            <button className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green transition text-sm">
                                Próximo <ChevronRight size={18} />
                            </button>
                        </div>
                    </div>
                </div>
            </main>
        </div>
    );
}