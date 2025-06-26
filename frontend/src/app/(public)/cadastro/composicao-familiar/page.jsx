'use client';

import { useState } from "react";
import SimpleTextField from "@/components/TextField/SimpleTextField";
import DatePickerInput from "@/components/DatePicker/DatePicker";
import RadioButtonWithLabel from "@/components/Button/RadioButtonWithLabel";
import {
  ChevronLeft, ChevronRight, ChevronUp,
  ChevronDown
} from 'lucide-react';
import Link from 'next/link';
import { Home, LayoutDashboard, User, UserPlus, Save } from 'lucide-react';

export default function ComposicaoFamiliar() {
  const [nome, setNome] = useState("");
  const [parentesco, setParentesco] = useState("");
  const [dataNascimento, setDataNascimento] = useState("");
  const [estuda, setEstuda] = useState(null); // 0 = Sim, 1 = Não
  const [ano, setAno] = useState("");
  const [trabalha, setTrabalha] = useState(null); // 0 = Sim, 1 = Não
  const [renda, setRenda] = useState("");
  const [componentes, setComponentes] = useState([]);
  const [listaAberta, setListaAberta] = useState(false);
  const [cadastroAberto, setCadastroAberto] = useState(true);

  const adicionarIntegrante = () => {
    if (nome.trim()) {
      setComponentes([
        ...componentes,
        { nome, parentesco, dataNascimento, estuda, ano, trabalha, renda }
      ]);
      setNome("");
      setParentesco("");
      setDataNascimento("");
      setEstuda(null);
      setAno("");
      setTrabalha(null);
      setRenda("");
    }
  };

  return (
    <div className="flex min-h-screen w-full text-main bg-background">
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

                  {/* Estuda */}
                  <div className="flex flex-col md:flex-row gap-0 mt-4">
                    <div className="flex items-center gap-1">
                      <span className="min-w-[90px] h-[35px] rounded-[10px] bg-success flex items-center justify-center px-2 text-background font-semibold">Estuda</span>
                      <RadioButtonWithLabel label="Sim" selected={estuda === 0} onClick={() => setEstuda(0)} />
                      <RadioButtonWithLabel label="Não" selected={estuda === 1} onClick={() => setEstuda(1)} />
                    </div>
                    {estuda === 0 && (
                      <div className="flex-1 ml-2">
                        <SimpleTextField nomeCampo="Ano" valorPreenchido={ano} onChange={setAno} />
                      </div>
                    )}
                  </div>

                  {/* Trabalha */}
                  <div className="flex flex-col md:flex-row gap-0 mt-4">
                    <div className="flex items-center gap-1">
                      <span className="min-w-[90px] h-[35px] rounded-[10px] bg-success flex items-center justify-center px-2 text-background font-semibold">Trabalha</span>
                      <RadioButtonWithLabel label="Sim" selected={trabalha === 0} onClick={() => setTrabalha(0)} />
                      <RadioButtonWithLabel label="Não" selected={trabalha === 1} onClick={() => setTrabalha(1)} />
                    </div>
                    {trabalha === 0 && (
                      <div className="flex-1 ml-2">
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
                    <div className="mt-2">
                      {componentes.length === 0 && (
                        <div className="text-center text-greendark text-[15px]">Nenhum integrante adicionado.</div>
                      )}
                      {componentes.map((item, idx) => (
                        <div key={idx} className="bg-paragraph rounded-md p-2 mb-2 relative">
                          <button
                            onClick={() => {
                              setComponentes((prev) => prev.filter((_, i) => i !== idx));
                            }}
                            className="absolute top-2 right-2 text-red-600 hover:text-red-800 text-sm underline"
                          >
                            Remover
                          </button>

                          <div className="text-[14px] text-greendark font-semibold">
                            Nome: <span className="font-normal">{item.nome}</span>
                          </div>
                          <div className="text-[14px] text-greendark font-semibold">
                            Parentesco: <span className="font-normal">{item.parentesco}</span>
                          </div>
                          <div className="text-[14px] text-greendark font-semibold">
                            Data de Nascimento:{" "}
                            <span className="font-normal">
                              {item.dataNascimento instanceof Date
                                ? item.dataNascimento.toLocaleDateString()
                                : item.dataNascimento}
                            </span>
                          </div>
                          <div className="text-[14px] text-greendark font-semibold">
                            Estuda: <span className="font-normal">{item.estuda === 0 ? "Sim" : "Não"}</span>
                          </div>
                          {item.estuda === 0 && (
                            <div className="text-[14px] text-greendark font-semibold">
                              Ano: <span className="font-normal">{item.ano}</span>
                            </div>
                          )}
                          <div className="text-[14px] text-greendark font-semibold">
                            Trabalha: <span className="font-normal">{item.trabalha === 0 ? "Sim" : "Não"}</span>
                          </div>
                          {item.trabalha === 0 && (
                            <div className="text-[14px] text-greendark font-semibold">
                              Renda: <span className="font-normal">{item.renda}</span>
                            </div>
                          )}
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
