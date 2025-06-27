'use client';
import Sidebar from '@/components/Sidebar/sidebar';
import { useState, useEffect } from "react";
import SimpleTextField from "@/components/TextField/SimpleTextField";
import DatePickerInput from "@/components/DatePicker/DatePicker";
import RadioButtonWithLabel from "@/components/Button/RadioButtonWithLabel";
import {
  ChevronLeft, ChevronRight,
} from 'lucide-react';
import Link from 'next/link';
import { UserPlus, Save } from 'lucide-react';

export default function ComposicaoFamiliar() {
  const [nome, setNome] = useState("");
  const [parentesco, setParentesco] = useState("");
  const [dataNascimento, setDataNascimento] = useState("");
  const [estuda, setEstuda] = useState(null); // 0 = Sim, 1 = Não
  const [ano, setAno] = useState("");
  const [ensinoSuperior, setEnsinoSuperior] = useState(null); // 0 = Sim, 1 = Não
  const [trabalha, setTrabalha] = useState(null); // 0 = Sim, 1 = Não
  const [renda, setRenda] = useState("");
  const [componentes, setComponentes] = useState([]);
  const [listaAberta, setListaAberta] = useState(false);
  const [cadastroAberto, setCadastroAberto] = useState(true);
  const [mediaRenda, setMediaRenda] = useState(0);

  // Atualiza média de renda sempre que a lista muda
  useEffect(() => {
    if (componentes.length === 0) {
      setMediaRenda(0);
      return;
    }
    const soma = componentes.reduce((acc, cur) => {
      const valor = parseFloat(cur.renda.replace(',', '.') || '0');
      return acc + (isNaN(valor) ? 0 : valor);
    }, 0);
    setMediaRenda(soma / componentes.length);
  }, [componentes]);

  const adicionarIntegrante = () => {
    if (nome.trim()) {
      setComponentes([
        ...componentes,
        { nome, parentesco, dataNascimento, estuda, ano, ensinoSuperior, trabalha, renda }
      ]);
      setNome("");
      setParentesco("");
      setDataNascimento("");
      setEstuda(null);
      setAno("");
      setEnsinoSuperior(null);
      setTrabalha(null);
      setRenda("");
    }
  };

  return (
    <div className="flex min-h-screen w-full text-main bg-background">
      <Sidebar />

      <div className="min-h-screen flex-1">
        <main className="p-6 mt-4">
          <div className="max-w-10xl mx-auto">
            <h1 className="flex items-center gap-2 text-2xl font-bold mb-6 text-center">
              Cadastro <UserPlus size={18} />
            </h1>

            <div className="bg-offwhite rounded-lg shadow max-h-[calc(88vh-200px)] flex flex-col">
              {/* Cabeçalho fixo */}
              <div className="p-6 border-b border-graymedium bg-offwhite sticky top-0 z-10">
                <h2 className="text-xl text-darkgray font-semibold mb-2">8. Composição Familiar</h2>
                <h3 className="text-darkgray">Informações de composição familiar paciente:</h3>
              </div>

              <div className="overflow-y-auto p-6 space-y-6 pr-2 flex-1">
                <div className="bg-offwhite p-6 rounded-lg shadow">
                  <h3 className="text-lg font-medium text-darkgray mb-4">Integrante</h3>
                  <div className="flex flex-col md:flex-row gap-4">
                    <SimpleTextField nomeCampo="Nome" valorPreenchido={nome} onChange={setNome} />
                    <SimpleTextField nomeCampo="Parentesco" valorPreenchido={parentesco} onChange={setParentesco} />
                    <DatePickerInput title="Data de Nascimento" value={dataNascimento} onChange={setDataNascimento} />
                  </div>

                  {/* Estuda e Ensino Superior juntos */}
                  <div className="flex flex-col md:flex-row gap-4 mt-4 items-center">
                    {/* Grupo Estuda */}
                    <div className="flex items-center gap-1 min-w-[180px]">
                      <span className="min-w-[90px] h-[35px] rounded-[10px] bg-success flex items-center justify-center px-2 text-background font-semibold">
                        Estuda
                      </span>
                      <RadioButtonWithLabel label="Sim" selected={estuda === 0} onClick={() => setEstuda(0)} />
                      <RadioButtonWithLabel label="Não" selected={estuda === 1} onClick={() => setEstuda(1)} />
                    </div>

                    {/* Grupo Ensino Superior, só aparece se estuda === 0 */}
                    {estuda === 0 && (
                      <div className="flex items-center gap-1 min-w-[220px]">
                        <span className="min-w-[120px] h-[35px] rounded-[10px] bg-success flex items-center justify-center px-2 text-background font-semibold">
                          Ensino Superior?
                        </span>
                        <RadioButtonWithLabel label="Sim" selected={ensinoSuperior === 0} onClick={() => setEnsinoSuperior(0)} />
                        <RadioButtonWithLabel label="Não" selected={ensinoSuperior === 1} onClick={() => setEnsinoSuperior(1)} />
                      </div>
                    )}
                  </div>

                  {/* Campo Ano fica abaixo, com espaçamento */}
                  {estuda === 0 && (
                    <div className="mt-4 w-full md:w-1/3">
                      <SimpleTextField nomeCampo="Ano" valorPreenchido={ano} onChange={setAno} />
                    </div>
                  )}

                  {/* Trabalha */}
                  <div className="flex flex-col md:flex-row gap-0 mt-4 items-center">
                    <div className="flex items-center gap-1">
                      <span className="min-w-[90px] h-[35px] rounded-[10px] bg-success flex items-center justify-center px-2 text-background font-semibold">Trabalha</span>
                      <RadioButtonWithLabel label="Sim" selected={trabalha === 0} onClick={() => setTrabalha(0)} />
                      <RadioButtonWithLabel label="Não" selected={trabalha === 1} onClick={() => setTrabalha(1)} />
                    </div>
                    {trabalha === 0 && (
                      <div className="flex-1 ml-4">
                        <SimpleTextField nomeCampo="Renda" valorPreenchido={renda} onChange={setRenda} />
                      </div>
                    )}
                  </div>

                  <div className="flex justify-center mt-6">
                    <button
                      className="w-2/3 bg-success text-background font-semibold rounded-[10px] py-2 text-[16px] hover:bg-green transition-colors"
                      onClick={adicionarIntegrante}
                    >
                      Adicionar integrante
                    </button>
                  </div>
                </div>

                {/* Média de renda */}
                <div className="bg-offwhite p-4 rounded-lg shadow mb-6">
                  <h3 className="text-md font-semibold text-darkgray">Média de Renda: R$ {mediaRenda.toFixed(2)}</h3>
                </div>

                {/* Lista de Integrantes */}
                <div className="bg-offwhite p-6 rounded-lg shadow">
                  <div
                    className="flex items-center justify-between cursor-pointer mb-2 bg-success rounded-md px-4 py-2"
                    onClick={() => setListaAberta(!listaAberta)}
                  >
                    <span className="text-background text-[16px] font-semibold">Familiares</span>
                    <span className="text-background text-[16px] font-medium">Quantidade: {componentes.length}</span>
                    <span className="text-background text-[22px] ml-2">{listaAberta ? "▲" : "▼"}</span>
                  </div>
                  {listaAberta && (
                    <div className="mt-2 max-h-96 overflow-auto">
                      {componentes.length === 0 && (
                        <div className="text-center text-greendark text-[15px]">Nenhum integrante adicionado.</div>
                      )}
                      {componentes.map((item, idx) => (
                        <div key={idx} className="bg-paragraph rounded-md p-2 mb-2 relative">
                          <button
                            onClick={() => {
                              if (window.confirm("Você deseja remover esse integrante?")) {
                                setComponentes((prev) => prev.filter((_, i) => i !== idx));
                              }
                            }}
                            className="absolute top-2 right-2 text-red-600 hover:text-red-800 text-sm underline"
                          >
                            Remover
                          </button>
                          {/* resto das infos */}
                        </div>
                      ))}
                    </div>
                  )}
                </div>
              </div>
            </div>
          </div>

          <div className="flex items-center mt-3 pt-6 border-t border-graymedium">
            <Link href="/cadastro/responsavel-opcional" className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green transition text-sm">
              <ChevronLeft size={18} /> Anterior
            </Link>

            <div className="flex-grow flex justify-center">
              <button className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green text-sm transition">
                <Save size={18} /> Salvar
              </button>
            </div>

            <Link href="/cadastro/historico-saude" className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green transition text-sm">
              Próximo <ChevronRight size={18} />
            </Link>
          </div>
        </main>
      </div>
    </div>
  );
}
