'use client';

import { useState, useEffect } from 'react';
import Link from 'next/link';
import {
    Home, LayoutDashboard, User, UserPlus, ChevronLeft, Save, ChevronRight, ChevronUp,
    ChevronDown
} from 'lucide-react';
import SimpleTextField from '@/components/TextField/SimpleTextField';

export default function EnderecoForm() {
    const [rua, setRua] = useState('');
    const [numero, setNumero] = useState('');
    const [bairro, setBairro] = useState('');
    const [cidade, setCidade] = useState('');
    const [estado, setEstado] = useState('');
    const [cep, setCep] = useState('');
    const [complemento, setComplemento] = useState('');
    const [carregandoCep, setCarregandoCep] = useState(false);
    const [erroCep, setErroCep] = useState('');
    const [cadastroAberto, setCadastroAberto] = useState(true);
    useEffect(() => {
        const buscarCep = async () => {
            if (cep.length === 8) {
                setCarregandoCep(true);
                setErroCep('');

                try {
                    const response = await fetch(`https://viacep.com.br/ws/${cep}/json/`);
                    const data = await response.json();

                    if (data.erro) {
                        setErroCep('CEP não encontrado.');
                        setRua('');
                        setBairro('');
                        setCidade('');
                        setEstado('');
                    } else {
                        setRua(data.logradouro || '');
                        setBairro(data.bairro || '');
                        setCidade(data.localidade || '');
                        setEstado(data.uf || '');
                    }
                } catch (error) {
                    setErroCep('Erro ao buscar CEP.');
                } finally {
                    setCarregandoCep(false);
                }
            }
        };

        buscarCep();
    }, [cep]);

    return (
        <div className="flex min-h-screen text-main bg-background">
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
            <main className="flex-1 p-6 mt-4">
                <div className="max-w-10xl mx-auto">
                    <h1 className="flex items-center gap-2 text-2xl font-bold mb-6 text-center">
                        Cadastro <UserPlus size={18} />
                    </h1>

                    <div className="bg-offwhite rounded-lg shadow max-h-[calc(88vh-200px)] flex flex-col">
                        {/* Cabeçalho fixo */}
                        <div className="p-6 border-b border-graymedium bg-offwhite sticky top-0 z-10">
                            <h2 className="text-xl text-darkgray font-semibold mb-2">11. Endereço</h2>
                            <h3 className="text-darkgray">Informações de endereco:</h3>
                        </div>
                        <div className="overflow-y-auto p-6 space-y-6 pr-2 flex-1">
                            <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
                                {/* CEP */}
                                <div className="bg-offwhite p-4 rounded-lg shadow">
                                    <SimpleTextField
                                        nomeCampo="CEP"
                                        valorPreenchido={cep}
                                        onChange={(value) => setCep(value.replace(/\D/g, '').slice(0, 8))}
                                        somenteNumero={true}
                                    />
                                    {carregandoCep && <p className="text-sm text-gray-500 mt-1">Buscando endereço...</p>}
                                    {erroCep && <p className="text-sm text-red-500 mt-1">{erroCep}</p>}
                                </div>

                                {/* Rua */}
                                <div className="bg-offwhite p-4 rounded-lg shadow">
                                    <SimpleTextField nomeCampo="Rua" valorPreenchido={rua} onChange={setRua} />
                                </div>

                                {/* Número */}
                                <div className="bg-offwhite p-4 rounded-lg shadow">
                                    <SimpleTextField nomeCampo="Número" valorPreenchido={numero} onChange={setNumero} somenteNumero={true} />
                                </div>

                                {/* Bairro */}
                                <div className="bg-offwhite p-4 rounded-lg shadow">
                                    <SimpleTextField nomeCampo="Bairro" valorPreenchido={bairro} onChange={setBairro} />
                                </div>

                                {/* Cidade */}
                                <div className="bg-offwhite p-4 rounded-lg shadow">
                                    <SimpleTextField nomeCampo="Cidade" valorPreenchido={cidade} onChange={setCidade} />
                                </div>

                                {/* Estado */}
                                <div className="bg-offwhite p-4 rounded-lg shadow">
                                    <SimpleTextField nomeCampo="Estado" valorPreenchido={estado} onChange={setEstado} />
                                </div>

                                {/* Complemento */}
                                <div className="bg-offwhite p-4 rounded-lg shadow md:col-span-2">
                                    <SimpleTextField nomeCampo="Complemento" valorPreenchido={complemento} onChange={setComplemento} />
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
                <div className="flex items-center mt-3 pt-6 border-t border-graymedium">
                    <Link href="/cadastro/situacao-habitacional" className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green transition text-sm">
                        <ChevronLeft size={18} /> Anterior
                    </Link>

                    <div className="flex-grow flex justify-center">
                        <button className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green text-sm transition">
                            <Save size={18} /> Salvar
                        </button>
                    </div>

                    <Link href="/cadastro/demais-dados" className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green transition text-sm">
                        Próximo <ChevronRight size={18} />
                    </Link>
                </div>
            </main>
        </div>
    );
}
