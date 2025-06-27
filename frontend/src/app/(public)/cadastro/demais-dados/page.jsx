"use client";
import Sidebar from '@/components/Sidebar/sidebar';
import { useState } from "react";
import SimpleTextField from "@/components/TextField/SimpleTextField";
import Link from 'next/link';
import {
  Home, LayoutDashboard, User, UserPlus, ChevronLeft, Save, ChevronUp,
  ChevronDown
} from 'lucide-react';

export default function DemaisDados() {
  const [comoConheceu, setComoConheceu] = useState("");
  const [descricaoObs, setDescricaoObs] = useState("");
  const [observacoes, setObservacoes] = useState([]);
  const [listaAberta, setListaAberta] = useState(false);
  const [cadastroAberto, setCadastroAberto] = useState(true);
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
      <Sidebar />
      <div className=" min-h-screen flex-1">
        <main className="p-6 mt-4">
          <div className="max-w-10xl mx-auto">
            <h1 className="flex items-center gap-2 text-2xl font-bold mb-6 text-center">Cadastro <UserPlus size={18} /></h1>
            <div className="bg-offwhite rounded-lg shadow max-h-[calc(88vh-200px)] flex flex-col">
              {/* Cabeçalho fixo */}
              <div className="p-6 border-b border-graymedium bg-offwhite sticky top-0 z-10">
                <h2 className="text-xl text-darkgray font-semibold mb-2">12. Demais Dados</h2>
              </div>
              <div className="overflow-y-auto p-6 space-y-6 pr-2 flex-1">
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
                    className="w-full bg-success text-background font-semibold rounded-[10px] py-2 my-2 text-[16px] border-2 border-main hover:bg-green transition"
                    onClick={adicionarObservacao}
                  >
                    Adicionar observação
                  </button>
                </div>
              </div>
            </div>
          </div>
          <div className="flex items-center mt-3 pt-6 border-t border-graymedium">
            <Link href="/cadastro/endereco" className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green transition text-sm">
              <ChevronLeft size={18} /> Anterior
            </Link>
            <div className="flex-grow flex justify-center"></div>
            <button className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green text-sm transition">
              <Save size={18} /> Salvar Paciente
            </button>
          </div>
        </main>
      </div>
    </div>
  );
} 