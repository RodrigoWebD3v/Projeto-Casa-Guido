'use client';
import Sidebar from '@/components/Sidebar/sidebar';
import { useState, useEffect } from 'react';
import DatePickerInput from '@/components/DatePicker/DatePicker';
import MultiOptionRadioGroup from '@/components/Button/MultiOptionRadioGroup';
import {
  ChevronLeft, ChevronRight, Save, UserPlus, X
} from 'lucide-react';
import Link from 'next/link';

export default function Radioterapia() {
  const [radioterapiaSelecionada, setRadioterapiaSelecionada] = useState('');
  const [dataInicio, setDataInicio] = useState(null);
  const [dataFim, setDataFim] = useState(null);
  const [radioterapias, setRadioterapias] = useState([]);
  const [aviso, setAviso] = useState(false);

  useEffect(() => {
    console.log('radioterapias:', radioterapias);
  }, [radioterapias]);

  const formatarData = (data) => {
    if (!data) return '';
    const dt = data instanceof Date ? data : new Date(data);
    if (isNaN(dt)) return '';
    const dia = dt.getDate().toString().padStart(2, '0');
    const mes = (dt.getMonth() + 1).toString().padStart(2, '0');
    const ano = dt.getFullYear();
    return `${dia}/${mes}/${ano}`;
  };

  const adicionarRadioterapia = () => {
    if (radioterapiaSelecionada === 'Sim') {
      if (!dataInicio) {
        setAviso(true);
        return;
      }
      if (dataFim && dataFim < dataInicio) {
        alert('A data de fim não pode ser anterior à data de início.');
        return;
      }

      const novaRadioterapia = {
        dataInicio,
        dataFim,
        fezRadioterapia: true,
      };

      setRadioterapias([...radioterapias, novaRadioterapia]);
      setRadioterapiaSelecionada('');
      setDataInicio(null);
      setDataFim(null);
      setAviso(false);
    } else if (radioterapiaSelecionada === 'Não') {
      setAviso(false);
      setRadioterapiaSelecionada('');
      setDataInicio(null);
      setDataFim(null);
    } else {
      setAviso(true);
    }
  };

  const excluirRadioterapia = (index) => {
    const confirmar = window.confirm('Tem certeza que deseja excluir esta radioterapia?');
    if (!confirmar) return;
    setRadioterapias(radioterapias.filter((_, idx) => idx !== index));
  };

  return (
    <div className="flex flex-col min-h-screen text-main bg-background">
      <div className="flex flex-1">
        {/* Menu lateral */}
        <Sidebar />

        {/* Conteúdo principal */}
        <main className="flex-1 p-6 mt-4">
          <div className="max-w-10xl mx-auto">
            <h1 className="flex items-center gap-2 text-2xl font-bold mb-6 text-center">
              Cadastro <UserPlus size={18} />
            </h1>

            <div className="bg-offwhite rounded-lg shadow max-h-[calc(88vh-200px)] flex flex-col">
              {/* Cabeçalho fixo */}
              <div className="p-6 border-b border-graymedium bg-offwhite sticky top-0 z-10">
                <h2 className="text-xl text-darkgray font-semibold mb-2">5. Radioterapia</h2>
                <h3 className="text-darkgray">Informações de Radioterapias do paciente:</h3>
              </div>

              <div className="overflow-y-auto p-6 space-y-6 pr-2 flex-1">
                <div className="flex flex-col md:flex-row md:items-start md:gap-6 mb-4">
                  <div className="flex-1">
                    <MultiOptionRadioGroup
                      labelTitulo="Radioterapia"
                      selected={radioterapiaSelecionada}
                      onChange={setRadioterapiaSelecionada}
                      options={[['Sim', 'Sim'], ['Não', 'Não']]}
                      inline={true}
                      className="gap-4"
                    />
                  </div>

                  <div className="flex-1 mt-4 md:mt-0">
                    {radioterapiaSelecionada === 'Sim' && (
                      <div className="bg-offwhite p-6 rounded-lg shadow text-background grid grid-cols-1 md:grid-cols-2 gap-4 mb-4">
                        <div>
                          <label className="block mb-1 font-medium">Data de início *</label>
                          <DatePickerInput value={dataInicio} onChange={setDataInicio} />
                        </div>
                        <div>
                          <label className="block mb-1 font-medium">Data de fim (opcional)</label>
                          <DatePickerInput
                            value={dataFim}
                            onChange={(date) => {
                              if (date && dataInicio && date < dataInicio) {
                                alert('A data de fim não pode ser anterior à data de início.');
                                return;
                              }
                              setDataFim(date);
                            }}
                            minDate={dataInicio}
                          />
                        </div>
                      </div>
                    )}

                    <div className="flex justify-end mr-4">
                      <button
                        onClick={adicionarRadioterapia}
                        className="bg-success text-background font-semibold py-2 px-6 rounded hover:bg-green transition"
                      >
                        Adicionar
                      </button>
                    </div>
                  </div>
                </div>

                {aviso && (
                  <div className="bg-yellow-100 border-l-4 border-yellow-500 text-yellow-800 p-3 rounded mt-4 flex justify-between items-start">
                    <span>Aviso: Preencha os campos obrigatórios antes de adicionar.</span>
                    <button
                      onClick={() => setAviso(false)}
                      className="ml-4 hover:text-yellow-900"
                      title="Fechar aviso"
                    >
                      <X size={16} />
                    </button>
                  </div>
                )}

                {/* Lista de radioterapias */}
                <div className="mt-6">
                  <h2 className="text-xl text-darkgray font-semibold mb-4">Radioterapias Cadastradas</h2>

                  {radioterapias.length === 0 ? (
                    <p className="text-darkgray">Nenhuma radioterapia cadastrada.</p>
                  ) : (
                    <div className="space-y-4">
                      {radioterapias.map((item, idx) => (
                        <div
                          key={idx}
                          className="bg-white p-4 rounded-lg flex justify-between items-center border border-graymedium text-darkgray shadow"
                        >
                          <div>
                            <p className="font-bold">Radioterapia {idx + 1}</p>
                            <p className="text-md text-background">
                              Início: {formatarData(item.dataInicio)}
                            </p>
                            <p className="text-md text-background">
                              {item.dataFim ? (
                                `Fim: ${formatarData(item.dataFim)}`
                              ) : (
                                <span className="text-green-700 font-semibold">Em andamento</span>
                              )}
                            </p>
                          </div>
                          <button
                            onClick={() => excluirRadioterapia(idx)}
                            className="text-red-500 hover:text-red-700 transition"
                            title="Remover"
                          >
                            <X size={20} />
                          </button>
                        </div>
                      ))}
                    </div>
                  )}
                </div>
              </div>
            </div>

            {/* Botões de navegação */}
            <div className="flex items-center mt-3 pt-6 border-t border-graymedium">
              <Link href="/cadastro/quimio" className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green transition text-sm">
                <ChevronLeft size={18} /> Anterior
              </Link>

              <div className="flex-grow flex justify-center">
                <button className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green text-sm transition">
                  <Save size={18} /> Salvar
                </button>
              </div>

              <Link href="/cadastro/responsavel" className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green transition text-sm">
                Próximo <ChevronRight size={18} />
              </Link>
            </div>
          </div>
        </main>
      </div>

      {/* Footer fixo ao final da estrutura */}
      <footer className="w-full mt-auto text-center text-xs text-sidebartext bg-sidebarbg p-6 shadow-[4px_0_8px_rgba(0,0,0,0.2)]">
        © {new Date().getFullYear()} Sistema de Gestão de Pacientes - Todos os direitos reservados.
      </footer>
    </div>
  );
}
