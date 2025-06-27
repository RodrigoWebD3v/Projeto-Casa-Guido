'use client';
import Sidebar from '@/components/Sidebar/sidebar';
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
            <Sidebar />
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
