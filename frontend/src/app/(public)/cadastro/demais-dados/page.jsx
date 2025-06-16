"use client";

import { useState } from "react";
import SimpleTextField from "@/components/TextField/SimpleTextField";

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
    <div className="min-h-screen bg-background flex flex-col items-center py-2">
      {/* Header */}
      <div className="w-full bg-main py-2 flex flex-col items-center">
        <span className="text-[22px] font-bold text-background">Cadastro <span className="ml-2">👤+</span></span>
      </div>
      {/* Barra de progresso fake */}
      <div className="w-full h-1 bg-success mb-2" />

      {/* Título e subtítulo */}
      <div className="w-full px-5 mb-2">
        <span className="block text-[18px] font-bold text-greendark">12. Demais dados</span>
        <span className="block text-[13px] text-greendark">Demais dados</span>
      </div>

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
      <button
        className="w-4/5 bg-main text-background font-semibold rounded-[10px] py-2 my-2 text-[16px] border-2 border-main hover:bg-success hover:text-main transition-colors"
        onClick={adicionarObservacao}
      >
        Adicionar observação
      </button>

      {/* Lista de observações */}
      <div className="w-4/5 rounded-[10px] bg-success flex items-center justify-between px-4 py-3 cursor-pointer mb-2" onClick={() => setListaAberta(!listaAberta)}>
        <span className="text-background text-[16px] font-semibold">Observações</span>
        <span className="text-greendark text-[16px] font-medium">Quantidade: {observacoes.length}</span>
        <span className="text-black text-[22px] ml-2">{listaAberta ? "▲" : "▼"}</span>
      </div>
      {listaAberta && (
        <div className="w-4/5 mb-2">
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
      <div className="w-full flex flex-row justify-between px-5 mt-2">
        <button className="flex-1 bg-paragraph text-greendark font-semibold rounded-[10px] py-2 mr-2">
          &#x276E; Anterior
        </button>
        <button className="flex-1 bg-success text-greendark font-semibold rounded-[10px] py-2 ml-2 flex items-center justify-center gap-2">
          <span>Salvar Paciente</span>
          <span className="text-[18px]">&#128190;</span>
        </button>
      </div>
    </div>
  );
} 