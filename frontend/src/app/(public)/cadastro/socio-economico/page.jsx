'use client';
import Sidebar from '@/components/Sidebar/sidebar';
import Link from 'next/link';
import {
    User, Home, LayoutDashboard, UserPlus, ChevronLeft, Save, ChevronRight, ChevronUp,
    ChevronDown
} from 'lucide-react';
import { useState, useEffect } from 'react';
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
    const [cadastroAberto, setCadastroAberto] = useState(true);
    const escolaridadeOptions = [
        { id: '2', nome: 'Fundamental' },
        { id: '3', nome: 'Médio' },
    ];

    const [serieOptions, setSerieOptions] = useState([]);

    useEffect(() => {
        if (escolaridade.nome === 'Fundamental') {
            setSerieOptions(Array.from({ length: 9 }, (_, i) => ({ id: `${i + 1}`, nome: `${i + 1}º` })));
        } else if (escolaridade.nome === 'Médio') {
            setSerieOptions(Array.from({ length: 3 }, (_, i) => ({ id: `${i + 1}`, nome: `${i + 1}º` })));
        } else {
            setSerieOptions([]);
        }
        setSerie({ id: '', nome: 'Série' });
    }, [escolaridade]);

    return (
        <div className="flex min-h-screen text-main bg-background">
            <Sidebar />
            <main className="flex-1 p-6 mt-4">
                <div className="max-w-10xl mx-auto">
                    <h1 className="flex items-center gap-2 text-2xl font-bold mb-6 text-center">
                        Cadastro <UserPlus size={18} />
                    </h1>

                    <div className="bg-offwhite rounded-lg shadow max-h-[calc(88vh-200px)] flex flex-col">
                        {/* Cabeçalho fixo */}
                        <div className="p-6 border-b border-graymedium bg-offwhite sticky top-0 z-10">
                            <h2 className="text-xl text-darkgray font-semibold mb-2">2. Socioeconômico</h2>
                            <h3 className="text-darkgray">Informações socioeconômicas do paciente:</h3>
                        </div>

                        {/* Conteúdo com scroll */}
                        <div className="overflow-y-auto p-6 space-y-6 pr-2 flex-1">
                            <div className="bg-offwhite p-6 rounded-lg shadow">
                                <h3 className="text-lg font-medium text-darkgray mb-4">Remuneração</h3>
                                <InputTextField
                                    nomeCampo="Digite o valor: R$"
                                    valorPreenchido={remuneracao}
                                    onChange={setRemuneracao}
                                    somenteNumero={true}
                                    className="bg-graylight border border-graymedium rounded-md p-2 transition focus:outline-none focus:ring-2 focus:ring-greenstrong w-full"
                                />
                            </div>

                            <div className="bg-offwhite p-6 rounded-lg shadow">
                                <MultiOptionRadioGroup
                                    labelTitulo="BPC"
                                    selected={bpc}
                                    onChange={setBpc}
                                    options={[['Não', 0], ['Sim', 1]]}
                                    inline={true}
                                    className="gap-4"
                                />
                                {bpc === 1 && (
                                    <div className="mt-4">
                                        <h3 className="text-lg font-medium text-darkgray mb-4">Valor</h3>
                                        <InputTextField
                                            nomeCampo="Digite o valor: R$"
                                            valorPreenchido={valor}
                                            onChange={setValor}
                                            somenteNumero={true}
                                            className="bg-graylight border border-graymedium rounded-md p-2 transition focus:outline-none focus:ring-2 focus:ring-greenstrong w-full"
                                        />
                                    </div>
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
                                        <div className="mt-2 ml-1">
                                            <MultiOptionRadioGroup
                                                labelTitulo="Escola"
                                                selected={escola}
                                                onChange={setEscola}
                                                options={[['Pública', 0], ['Privada', 1]]}
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
                                                disabled={serieOptions.length === 0}
                                                className="bg-offwhite border border-graymedium rounded-md flex-1 p-2 transition focus:outline-none focus:ring-2 focus:ring-greenstrong"
                                            />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    {/* Botões finais */}
                    <div className="flex items-center mt-3 pt-6 border-t border-graymedium">
                        <Link
                            href="/cadastro/identificacao-paciente"
                            className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green transition text-sm"
                        >
                            <ChevronLeft size={18} /> Anterior
                        </Link>

                        <div className="flex-grow flex justify-center">
                            <button className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green text-sm transition">
                                <Save size={18} /> Salvar
                            </button>
                        </div>

                        <Link
                            href="/cadastro/cirurgias"
                            className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green transition text-sm"
                        >
                            Próximo <ChevronRight size={18} />
                        </Link>
                    </div>
                </div>
            </main>
        </div>
    );
}
