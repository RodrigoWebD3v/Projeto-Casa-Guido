'use client';

import Link from 'next/link';
import { User, Home, LayoutDashboard } from 'lucide-react';
import { useState, useEffect } from 'react';
import { ChevronLeft, Save, ChevronRight } from 'lucide-react';
import DropDownMenu from '@/components/DropDownMenu/DropDrownMenu';
import InputTextField from '@/components/TextField/InputTextField';
import SimpleTextField from '@/components/TextField/SimpleTextField';
import MultiOptionRadioGroup from '@/components/Button/MultiOptionRadioGroup';

export default function SocioEconomicForm() {
    const [remuneracao, setRemuneracao] = useState(null);
    const [bpc, setBpc] = useState(null);
    const [valor, setValor] = useState(null);
    const [instituicaoEnsino, setInstituicaoEnsino] = useState(null);
    const [escolaridade, setEscolaridade] = useState({ id: '', nome: 'Nível' });
    const [serie, setSerie] = useState({ id: '', nome: 'Série' });
    const [escola, setEscola] = useState(null);

    const escolaridadeOptions = [
        { id: '2', nome: 'Fundamental' },
        { id: '3', nome: 'Médio' },
    ];

    const [serieOptions, setSerieOptions] = useState([]);

    useEffect(() => {
        if (escolaridade.nome === 'Fundamental') {
            const op = Array.from({ length: 9 }, (_, i) => ({
                id: (i + 1).toString(),
                nome: `${i + 1}º`,
            }));
            setSerieOptions(op);
            setSerie({ id: '', nome: 'Série' });
        } else if (escolaridade.nome === 'Médio') {
            const op = Array.from({ length: 3 }, (_, i) => ({
                id: (i + 1).toString(),
                nome: `${i + 1}º`,
            }));
            setSerieOptions(op);
            setSerie({ id: '', nome: 'Série' });
        } else {
            setSerieOptions([]);
            setSerie({ id: '', nome: 'Série' });
        }
    }, [escolaridade]);

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

            <main className="flex-1 p-6 mt-4 ">
                <div className="max-w-10xl mx-auto">
                    <h1 className="text-2xl font-bold text-main mb-2">Cadastro</h1>
                    <div className="bg-offwhite p-6 rounded-lg shadow">
                        <h2 className="text-xl text-darkgray font-semibold mb-4">2. Sócio econômico</h2>
                        <h3 className="text-darkgray mb-8">Informações sócio econômicas do paciente:</h3>

                        <div className="space-y-6">
                            <div className="bg-offwhite p-6 rounded-lg shadow">
                                <h3 className="text-lg font-medium text-darkgray mb-4">Remuneração</h3>
                                <div className="flex items-center gap-4">
                                    <InputTextField
                                        nomeCampo="Digite o valor: R$"
                                        valorPreenchido={remuneracao}
                                        onChange={setRemuneracao}
                                        somenteNumero={true}
                                        className="bg-graylight border border-graymedium rounded-md p-2 transition focus:outline-none focus:ring-2 focus:ring-greenstrong w-full"
                                    />
                                </div>
                            </div>

                            <div className="flex flex-col justify-start bg-offwhite p-6 rounded-lg shadow">
                                <div>
                                    <MultiOptionRadioGroup
                                        labelTitulo="BPC"
                                        selected={bpc}
                                        onChange={setBpc}
                                        options={[["Não", 0], ["Sim", 1]]}
                                        inline={true}
                                        className="gap-4"
                                    />
                                </div>
                                {bpc === 1 && (
                                    <>
                                        <h3 className="text-lg font-medium text-darkgray mb-4 mt-4">Valor</h3>
                                        <div>
                                            <InputTextField
                                                nomeCampo="Digite o valor: R$"
                                                valorPreenchido={valor}
                                                onChange={setValor}
                                                somenteNumero={true}
                                                className="bg-graylight border border-graymedium rounded-md p-2 transition focus:outline-none focus:ring-2 focus:ring-greenstrong w-full"
                                            />
                                        </div>
                                    </>
                                )}
                            </div>

                            <div className="bg-offwhite p-6 rounded-lg shadow">
                                <h3 className="text-lg font-medium text-darkgray mb-4">Escolaridade</h3>

                                <div className="flex gap-6">
                                    <div className="w-1/2">
                                        <SimpleTextField
                                            nomeCampo="Instituição de ensino"
                                            placeholder="Nome da instituição"
                                            valorPreenchido={instituicaoEnsino}
                                            onChange={setInstituicaoEnsino}
                                            className="bg-graylight border border-graymedium rounded-md p-2 transition focus:outline-none focus:ring-2 focus:ring-greenstrong"
                                        />

                                        <div className="mt-2 ml-1"> { }
                                            <MultiOptionRadioGroup
                                                labelTitulo="Escola"
                                                selected={escola}
                                                onChange={setEscola}
                                                options={[["Pública", 0], ["Privada", 1]]}
                                                className="gap-4"
                                            />
                                        </div>
                                    </div>


                                    <div className="w-1/2 flex flex-col gap-4 border border-graymedium rounded-md p-4 bg-graylight">
                                        <div className="flex items-center gap-2">
                                            <p className="text-sm font-medium text-darkgray w-20">Nível:</p>
                                            <DropDownMenu
                                                options={escolaridadeOptions}
                                                onSelected={setEscolaridade}
                                                selected={escolaridade.id === '' ? { id: '', nome: 'Nível' } : escolaridade}
                                                className="bg-offwhite border border-graymedium rounded-md flex-1 p-2 transition focus:outline-none focus:ring-2 focus:ring-greenstrong"
                                            />
                                        </div>

                                        <div className="flex items-center gap-2">
                                            <p className="text-sm font-medium text-darkgray w-20">Série:</p>
                                            <DropDownMenu
                                                options={serieOptions}
                                                onSelected={setSerie}
                                                selected={serie.id === '' ? { id: '', nome: 'Série' } : serie}
                                                className="bg-offwhite border border-graymedium rounded-md flex-1 p-2 transition focus:outline-none focus:ring-2 focus:ring-greenstrong"
                                                disabled={serieOptions.length === 0}
                                            />
                                        </div>
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
