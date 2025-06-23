"use client";

import { useState } from "react";
import SimpleTextField from "@/components/TextField/SimpleTextField";
import Link from 'next/link';
import { Home, LayoutDashboard, User, UserPlus, ChevronLeft, Save } from 'lucide-react';

export default function DemaisDados() {
  const [comoConheceu, setComoConheceu] = useState("");
  const [descricaoObs, setDescricaoObs] = useState("");
  const [observacoes, setObservacoes] = useState([]);
  const [listaAberta, setListaAberta] = useState(false);

  const adicionarObservacao = () => {
    if (comoConheceu.trim() || descricaoObs.trim()) {
      setObservacoes([
        ...observacoes,
        { comoConheceu, descricaoObs }
      ]);
      setComoConheceu("");
      setDescricaoObs("");
    }
  };

  return (
    <div className="flex min-h-screen w-full text-main bg-background">
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
      <div className="bg-background min-h-screen flex-1">
        <main className="p-6 mt-4">
          <div className="max-w-10xl mx-auto">
            <h1 className="flex items-center gap-2 text-2xl font-bold mb-6 text-center">Cadastro <UserPlus size={18} /></h1>
            <div className="bg-offwhite p-6 rounded-lg shadow">
              <h2 className="text-xl text-darkgray font-semibold mb-4">12. Demais dados</h2>
              <h3 className="text-darkgray mb-8">Demais dados</h3>
              {/* Campos de texto */}
              <SimpleTextField
                nomeCampo="Como conheceu a ONG Guido"
                valorPreenchido={comoConheceu}
                onChange={setComoConheceu}
              />
              <SimpleTextField
                nomeCampo="Descrição da observação"
                valorPreenchido={descricaoObs}
                onChange={setDescricaoObs}
              />
              {/* Botão adicionar observação */}
              <div className="flex justify-center">
                <button
                  className="w-full bg-main text-background font-semibold rounded-[10px] py-2 my-2 text-[16px] border-2 border-main hover:bg-success hover:text-main transition-colors"
                  onClick={adicionarObservacao}
                >
                  Adicionar observação
                </button>
              </div>
              {/* Lista de observações */}
              <div className="w-full rounded-[10px] bg-success flex items-center justify-between px-4 py-3 cursor-pointer mb-2" onClick={() => setListaAberta(!listaAberta)}>
                <span className="text-background text-[16px] font-semibold">Observações</span>
                <span className="text-greendark text-[16px] font-medium">Quantidade: {observacoes.length}</span>
                <span className="text-black text-[22px] ml-2">{listaAberta ? "▲" : "▼"}</span>
              </div>
              {listaAberta && (
                <div className="w-full mb-2">
                  {observacoes.length === 0 && (
                    <div className="text-center text-greendark text-[15px]">Nenhuma observação adicionada.</div>
                  )}
                  {observacoes.map((obs, idx) => (
                    <div key={idx} className="bg-paragraph rounded-md p-2 mb-2">
                      <div className="text-[14px] text-greendark font-semibold">Como conheceu: <span className="font-normal">{obs.comoConheceu}</span></div>
                      <div className="text-[14px] text-greendark font-semibold">Descrição: <span className="font-normal">{obs.descricaoObs}</span></div>
                    </div>
                  ))}
                </div>
              )}
              {/* Botões de navegação */}
              <div className="flex items-center mt-8 pt-6 border-t border-graymedium">
                <button className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green text-sm transition">
                  <ChevronLeft size={18} /> Anterior
                </button>
                <div className="flex-grow flex justify-center"></div>
                <button className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green text-sm transition">
                  <Save size={18} /> Salvar Paciente
                </button>
              </div>
            </div>
          </div>
        </main>
      </div>
    </div>
  );
} 