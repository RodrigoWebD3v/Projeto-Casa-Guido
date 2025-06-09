'use client';

import { useState, useEffect } from 'react';
import { ChevronLeft, Save, ChevronRight } from 'lucide-react';
import DropDownMenu from '@/components/DropDownMenu/DropDrownMenu';
import InputTextField from '@/components/TextField/InputTextField';
import SimpleTextField from '@/components/TextField/SimpleTextField';
import MultiOptionRadioGroup from '@/components/Button/MultiOptionRadioGroup';

export default function SocioEconomicForm() {
    // Estado para os campos do formulário
    const [remuneracao, setRemuneracao] = useState(null);
    const [bpc, setBpc] = useState(null);
    const [valor, setValor] = useState(null);
    const [instituicaoEnsino, setInstituicaoEnsino] = useState('E.E.B Ignacio Stakowski');
    const [escolaridade, setEscolaridade] = useState({ id: '', nome: 'Selecione uma opção' });
    const [serie, setSerie] = useState({ id: '', nome: 'Selecione uma opção' });
    const [escola, setEscola] = useState(null);

    // Opções para os dropdowns
    const escolaridadeOptions = [
        { id: '1', nome: 'Fundamental' },
        { id: '2', nome: 'Médio' },
        // Removi a opção 'Superior'
    ];

    // Séries dinâmicas conforme a escolaridade
    const [serieOptions, setSerieOptions] = useState([]);

    // Atualiza as opções da série com base na escolaridade selecionada
    useEffect(() => {
        if (escolaridade.nome === 'Fundamental') {
            // 1 a 9
            const op = Array.from({ length: 9 }, (_, i) => ({
                id: (i + 1).toString(),
                nome: `${i + 1}º`,
            }));
            setSerieOptions(op);
            setSerie({ id: '', nome: 'Selecione uma opção' }); // reset série
        } else if (escolaridade.nome === 'Médio') {
            // 1 a 3
            const op = Array.from({ length: 3 }, (_, i) => ({
                id: (i + 1).toString(),
                nome: `${i + 1}º`,
            }));
            setSerieOptions(op);
            setSerie({ id: '', nome: 'Selecione uma opção' }); // reset série
        } else {
            setSerieOptions([]);
            setSerie({ id: '', nome: 'Selecione uma opção' });
        }
    }, [escolaridade]);

    return (
        <div className="flex min-h-screen text-main">
            {/* Sidebar */}
            <aside className="w-44 bg-gray-700 shadow-sm p-6">
                <h2 className="text-xl font-bold mb-6 flex items-center gap-2 text-white">
                    Menu
                </h2>
                <nav className="flex flex-col gap-4">
                    <a href="#" className="flex items-center gap-2 p-2 rounded border border-transparent hover:border-white hover:bg-gray-400 hover:text-green-600 transition text-white">
                        Cadastros
                    </a>
                    <a href="#" className="flex items-center gap-2 p-2 rounded border border-transparent hover:border-white hover:bg-gray-400 hover:text-green-600 transition text-white">
                        Pacientes
                    </a>
                </nav>
            </aside>

            {/* Conteúdo principal */}
            <main className="flex-1 p-8 bg-background">
                <div className="max-w-4xl mx-auto">
                    <h1 className="text-2xl font-bold text-main mb-2">Cadastro</h1>
                    <h2 className="text-xl font-semibold text-main-700 mb-4">2. Sócio econômico</h2>
                    <h3 className="text-main mb-8">Informações sócio econômicas do paciente:</h3>

                    <div className="space-y-6">
                        {/* Seção Remuneração */}
                        <div className="bg-white p-6 rounded-lg shadow">
                            <h3 className="text-lg font-medium text-gray-700 mb-4">Remuneração</h3>
                            <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
                                <InputTextField
                                    nomeCampo="Digite o valor: R$"
                                    valorPreenchido={remuneracao}
                                    onChange={setRemuneracao}
                                    somenteNumero={true}
                                    className="bg-gray-50 border border-gray-300 rounded-md p-2 transition focus:outline-none focus:ring-2 focus:ring-green-600"
                                    style={{ boxSizing: 'border-box' }}
                                />
                            </div>
                        </div>

                        {/* Seção Valor e Instituição */}
                        <div className="flex flex-col justify-start bg-white p-6 rounded-lg shadow">
                            <div>
                                <MultiOptionRadioGroup
                                    labelTitulo="BPC"
                                    selected={bpc}
                                    onChange={setBpc}
                                    options={[
                                        ['Não', 0],
                                        ['Sim', 1],
                                    ]}
                                    inline={true}
                                    className="gap-4"
                                />
                            </div>
                            {/* Mostrar input só se BPC for "Sim" */}
                            {bpc === 1 && (
                                <>
                                    <h3 className="text-lg font-medium text-gray-700 mb-4 mt-4">Valor</h3>
                                    <div>
                                        <InputTextField
                                            nomeCampo="Digite o valor: R$"
                                            valorPreenchido={valor}
                                            onChange={setValor}
                                            somenteNumero={true}
                                        />
                                    </div>
                                </>
                            )}
                        </div>


                        {/* Seção Escolaridade */}
                        <div className="bg-white p-6 rounded-lg shadow">
                            <h3 className="text-lg font-medium text-gray-700 mb-4">Escolaridade</h3>

                            <div className="flex gap-6">
                                {/* Coluna da esquerda: Instituição de ensino */}
                                <div className="w-1/2">
                                    <SimpleTextField
                                        nomeCampo="Instituição de ensino"
                                        placeholder="Nome da instituição"
                                        valorPreenchido={instituicaoEnsino}
                                        onChange={setInstituicaoEnsino}
                                        className="bg-gray-50 border border-gray-300 rounded-md p-2 transition focus:outline-none focus:ring-2 focus:ring-green-600"
                                    />
                                </div>

                                {/* Coluna da direita: Dropdowns empilhados */}
                                <div className="w-1/2 flex flex-col gap-4">
                                    <div>
                                        <p className="text-sm font-medium text-gray-700 mb-2">Nível</p>
                                        <DropDownMenu
                                            options={escolaridadeOptions}
                                            onSelected={setEscolaridade}
                                            selected={escolaridade}
                                            className="bg-gray-50 border border-gray-300 rounded-md w-full p-2 transition focus:outline-none focus:ring-2 focus:ring-green-600"
                                        />
                                    </div>

                                    <div>
                                        <p className="text-sm font-medium text-gray-700 mb-2">Série</p>
                                        <DropDownMenu
                                            options={serieOptions}
                                            onSelected={setSerie}
                                            selected={serie}
                                            className="bg-gray-50 border border-gray-300 rounded-md w-full p-2 transition focus:outline-none focus:ring-2 focus:ring-green-600"
                                            disabled={serieOptions.length === 0}
                                        />
                                    </div>
                                </div>
                            </div>
                        </div>

                        {/* Seção Tipo de Escola */}
                        <div className="bg-white p-6 rounded-lg shadow">
                            <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
                                <div>
                                    <MultiOptionRadioGroup
                                        labelTitulo="Escola"
                                        selected={escola}
                                        onChange={setEscola}
                                        options={[
                                            ['Pública', 0],
                                            ['Privada', 1],
                                        ]}
                                        inline={true}
                                        className="gap-4"
                                    />
                                </div>
                            </div>
                        </div>
                    </div>

                    {/* Botões de navegação */}
                    <div className="flex justify-between mt-8 pt-6 border-t border-gray-200">
                        <button className="flex items-center gap-2 px-6 py-2 bg-gray-200 text-gray-700 rounded-md hover:bg-gray-300 transition">
                            <ChevronLeft size={18} /> Anterior
                        </button>
                        <div className="flex gap-4">
                            <button className="flex items-center gap-2 px-6 py-2 bg-gray-200 text-gray-700 rounded-md hover:bg-gray-300 transition">
                                <Save size={18} /> Salvar
                            </button>
                            <button className="flex items-center gap-2 px-6 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700 transition">
                                Próximo <ChevronRight size={18} />
                            </button>
                        </div>
                    </div>
                </div>
            </main>
        </div>
    );
}
