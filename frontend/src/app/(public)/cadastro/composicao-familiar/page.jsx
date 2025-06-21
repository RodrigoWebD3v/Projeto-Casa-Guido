"use client";

import { useState } from "react";
import SimpleTextField from "@/components/TextField/SimpleTextField";
import DatePickerInput from "@/components/DatePicker/DatePicker";
import RadioButtonWithLabel from "@/components/Button/RadioButtonWithLabel";
import { ChevronLeft, ChevronRight } from 'lucide-react';
import Link from 'next/link';
import { Home, LayoutDashboard, User, UserPlus } from 'lucide-react';

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

      <div className="bg-background min-h-screen flex-1">
        <main className="p-6 mt-4">
          <div className="max-w-10xl mx-auto">
            <h1 className="flex items-center gap-2 text-2xl font-bold mb-6 text-center">Cadastro <UserPlus size={18} /></h1>
            <div className="bg-offwhite p-6 rounded-lg shadow">
              <h2 className="text-xl text-darkgray font-semibold mb-4">9. Composição Familiar</h2>
              <h3 className="text-darkgray mb-8">Informações da composição familiar:</h3>

              <div className="space-y-6">
                <div className="bg-offwhite p-6 rounded-lg shadow">
                  <h3 className="text-lg font-medium text-darkgray mb-4">Integrante</h3>
                  <div className="flex flex-col md:flex-row gap-4">
                    <div className="flex-1">
                      <SimpleTextField
                        nomeCampo="Nome"
                        valorPreenchido={nome}
                        onChange={setNome}
                      />
                    </div>
                    <div className="flex-1">
                      <SimpleTextField
                        nomeCampo="Parentesco"
                        valorPreenchido={parentesco}
                        onChange={setParentesco}
                      />
                    </div>
                    <div className="flex-1">
                      <DatePickerInput
                        title="Data de Nascimento"
                        value={dataNascimento}
                        onChange={setDataNascimento}
                      />
                    </div>
                  </div>
                  <div className="flex flex-col md:flex-row gap-0 mt-0">
                    <div className="flex items-center gap-1">
                      <span className="min-w-[90px] h-[35px] rounded-[10px] bg-success flex items-center justify-center px-2 text-background font-semibold">Estuda</span>
                      <RadioButtonWithLabel label="Sim" selected={estuda === 0} onClick={() => setEstuda(0)} />
                      <RadioButtonWithLabel label="Não" selected={estuda === 1} onClick={() => setEstuda(1)} />
                    </div>
                    <div className="flex-1 ml-2">
                      <SimpleTextField
                        nomeCampo="Ano"
                        valorPreenchido={ano}
                        onChange={setAno}
                      />
                    </div>
                  </div>
                  <div className="flex flex-col md:flex-row gap-0 mt-0">
                    <div className="flex items-center gap-1">
                      <span className="min-w-[90px] h-[35px] rounded-[10px] bg-success flex items-center justify-center px-2 text-background font-semibold">Trabalha</span>
                      <RadioButtonWithLabel label="Sim" selected={trabalha === 0} onClick={() => setTrabalha(0)} />
                      <RadioButtonWithLabel label="Não" selected={trabalha === 1} onClick={() => setTrabalha(1)} />
                    </div>
                    <div className="flex-1 ml-2">
                      <SimpleTextField
                        nomeCampo="Renda"
                        valorPreenchido={renda}
                        onChange={setRenda}
                      />
                    </div>
                  </div>
                  <div className="flex justify-center mt-6">
                    <button
                      className="w-2/3 bg-[#08544f] text-white font-semibold rounded-[10px] py-2 text-[16px] border-2 border-[#08544f] hover:bg-success hover:text-main transition-colors"
                      onClick={adicionarIntegrante}
                    >
                      Adicionar integrante
                    </button>
                  </div>
                </div>

                <div className="bg-offwhite p-6 rounded-lg shadow">
                  <div className="flex items-center justify-between cursor-pointer mb-2 bg-success rounded-md px-4 py-2" onClick={() => setListaAberta(!listaAberta)}>
                    <span className="text-background text-[16px] font-semibold">Componentes</span>
                    <span className="text-background text-[16px] font-medium">Quantidade: {componentes.length}</span>
                    <span className="text-background text-[22px] ml-2">{listaAberta ? "▲" : "▼"}</span>
                  </div>
                  {listaAberta && (
                    <div className="mt-2">
                      {componentes.length === 0 && (
                        <div className="text-center text-greendark text-[15px]">Nenhum integrante adicionado.</div>
                      )}
                      {componentes.map((item, idx) => (
                        <div key={idx} className="bg-paragraph rounded-md p-2 mb-2">
                          <div className="text-[14px] text-greendark font-semibold">Nome: <span className="font-normal">{item.nome}</span></div>
                          <div className="text-[14px] text-greendark font-semibold">Parentesco: <span className="font-normal">{item.parentesco}</span></div>
                          <div className="text-[14px] text-greendark font-semibold">Data de Nascimento: <span className="font-normal">{item.dataNascimento}</span></div>
                          <div className="text-[14px] text-greendark font-semibold">Estuda: <span className="font-normal">{item.estuda === 0 ? "Sim" : "Não"}</span></div>
                          <div className="text-[14px] text-greendark font-semibold">Ano: <span className="font-normal">{item.ano}</span></div>
                          <div className="text-[14px] text-greendark font-semibold">Trabalha: <span className="font-normal">{item.trabalha === 0 ? "Sim" : "Não"}</span></div>
                          <div className="text-[14px] text-greendark font-semibold">Renda: <span className="font-normal">{item.renda}</span></div>
                        </div>
                      ))}
                    </div>
                  )}
                </div>
              </div>

              <div className="flex items-center mt-8 pt-6 border-t border-graymedium">
                <button className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green text-sm transition">
                  <ChevronLeft size={18} /> Anterior
                </button>
                <div className="flex-grow flex justify-center"></div>
                <button className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green text-sm transition">
                  Próximo <ChevronRight size={18} />
                </button>
              </div>
            </div>
          </div>
        </main>
      </div>
    </div>
  );
} 