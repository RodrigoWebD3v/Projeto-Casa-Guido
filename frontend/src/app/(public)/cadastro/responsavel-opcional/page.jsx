'use client';

import { useState } from 'react';
import Link from 'next/link';
import { Home, LayoutDashboard, User, UserPlus, ChevronLeft, Save, ChevronRight } from 'lucide-react';
import SimpleTextField from '@/components/TextField/SimpleTextField';
import DatePickerInput from '@/components/DatePicker/DatePicker';
import MultiOptionRadioGroup from '@/components/Button/MultiOptionRadioGroup';

export default function ResponsavelOpcionalForm() {
    const [nomeCompleto, setNomeCompleto] = useState('');
    const [cpf, setCpf] = useState('');
    const [rg, setRg] = useState('');
    const [naturalidade, setNaturalidade] = useState('');
    const [escolaridade, setEscolaridade] = useState('');
    const [dataNascimento, setDataNascimento] = useState('');
    const [celular, setCelular] = useState('');
    const [cartaoSus, setCartaoSus] = useState('');
    const [salario, setSalario] = useState('');
    const [estadoCivil, setEstadoCivil] = useState(null);

    return (
        <div className="flex min-h-screen text-main bg-background">
            <aside
                className="w-44 bg-darkgray p-6"
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
                        <h2 className="text-xl text-darkgray font-semibold mb-4">7. Responsável (Opcional)</h2>
                        <h3 className="text-darkgray mb-4">Preencha se houver um segundo responsável:</h3>

                        <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                            {/* Nome Completo */}
                            <div className="bg-offwhite p-4 rounded-lg shadow">
                                <SimpleTextField nomeCampo="Nome Completo" valorPreenchido={nomeCompleto} onChange={setNomeCompleto} />
                            </div>

                            {/* CPF */}
                            <div className="bg-offwhite p-4 rounded-lg shadow">
                                <SimpleTextField nomeCampo="Cpf" valorPreenchido={cpf} onChange={setCpf} somenteNumero={true} />
                            </div>

                            {/* RG */}
                            <div className="bg-offwhite p-4 rounded-lg shadow">
                                <SimpleTextField nomeCampo="Rg" valorPreenchido={rg} onChange={setRg} />
                            </div>

                            {/* Naturalidade */}
                            <div className="bg-offwhite p-4 rounded-lg shadow">
                                <SimpleTextField nomeCampo="Naturalidade" valorPreenchido={naturalidade} onChange={setNaturalidade} />
                            </div>

                            {/* Escolaridade */}
                            <div className="bg-offwhite p-4 rounded-lg shadow">
                                <SimpleTextField nomeCampo="Escolaridade" valorPreenchido={escolaridade} onChange={setEscolaridade} />
                            </div>

                            {/* Data de Nascimento */}
                            <div className="bg-offwhite p-4 rounded-lg shadow">
                                <DatePickerInput title="Data de Nascimento" value={dataNascimento} onChange={setDataNascimento} />
                            </div>

                            {/* Celular */}
                            <div className="bg-offwhite p-4 rounded-lg shadow">
                                <SimpleTextField nomeCampo="Celular" valorPreenchido={celular} onChange={setCelular} somenteNumero={true} />
                            </div>

                            {/* Cartão SUS */}
                            <div className="bg-offwhite p-4 rounded-lg shadow">
                                <SimpleTextField nomeCampo="Cartão do SUS" valorPreenchido={cartaoSus} onChange={setCartaoSus} somenteNumero={true} />
                            </div>

                            {/* Salário */}
                            <div className="bg-offwhite p-4 rounded-lg shadow">
                                <SimpleTextField nomeCampo="Salário" valorPreenchido={salario} onChange={setSalario} somenteNumero={true} />
                            </div>
                        </div>

                        {/* Estado Civil */}
                        <div className="bg-offwhite p-4 rounded-lg shadow mt-6">
                            <MultiOptionRadioGroup
                                labelTitulo="Estado Civil"
                                selected={estadoCivil}
                                onChange={setEstadoCivil}
                                options={[['Solteiro', 0], ['Casado', 1], ['Viúvo', 2], ['Separado', 3]]}
                                inline={true}
                            />
                        </div>

                        {/* Botões */}
                        <div className="flex items-center mt-8 pt-6 border-t border-graymedium">
                            <Link href="/cadastro/responsavel" className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green transition text-sm">
                                <ChevronLeft size={18} /> Anterior
                            </Link>

                            <div className="flex-grow flex justify-center">
                                <button className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green text-sm transition">
                                    <Save size={18} /> Salvar
                                </button>
                            </div>

                            <Link href="/cadastro/composicao-familiar" className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green transition text-sm">
                                Próximo <ChevronRight size={18} />
                            </Link>
                        </div>
                    </div>
                </div>
            </main>
        </div>
    );
}
