"use client";

import { useState } from "react";
import SimpleTextField from "@/components/TextField/SimpleTextField";
import DatePickerInput from "@/components/DatePicker/DatePicker";
import RadioButtonWithLabel from "@/components/Button/RadioButtonWithLabel";

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
    <div className="min-h-screen bg-background flex flex-col items-center py-2">
      {/* Header */}
      <div className="w-full bg-main py-2 flex flex-col items-center">
        <span className="text-[22px] font-bold text-background">Cadastro <span className="ml-2">👤+</span></span>
      </div>
      {/* Barra de progresso fake */}
      <div className="w-full h-1 bg-success mb-2" />

      {/* Título e subtítulo */}
      <div className="w-full px-5 mb-2">
        <span className="block text-[18px] font-bold text-greendark">9. Composição Familiar</span>
        <span className="block text-[13px] text-greendark">Informações da composição familiar</span>
      </div>

      {/* Nome */}
      <SimpleTextField
        nomeCampo="Nome"
        valorPreenchido={nome}
        onChange={setNome}
      />

      {/* Parentesco e Data de Nascimento */}
      <div className="w-full flex flex-row gap-2 px-5 mb-2">
        <div className="flex-1">
          <SimpleTextField
            nomeCampo="Parentesco"
            valorPreenchido={parentesco}
            onChange={setParentesco}
          />
        </div>
        <div className="flex-1 flex flex-col justify-end">
          <DatePickerInput
            title="Data de Nascimento"
            value={dataNascimento}
            onChange={setDataNascimento}
          />
        </div>
      </div>

      {/* Estuda */}
      <div className="w-full flex flex-row items-center px-5 mb-2">
        <div className="min-w-[90px] h-[35px] rounded-[10px] bg-success flex items-center justify-center px-2 mr-2">
          <span className="text-background text-[16px] font-semibold text-center">Estuda</span>
        </div>
        <div className="flex flex-row gap-4 ml-2">
          <RadioButtonWithLabel label="Sim" selected={estuda === 0} onClick={() => setEstuda(0)} />
          <RadioButtonWithLabel label="Não" selected={estuda === 1} onClick={() => setEstuda(1)} />
        </div>
      </div>

      {/* Ano */}
      <SimpleTextField
        nomeCampo="Ano"
        valorPreenchido={ano}
        onChange={setAno}
      />

      {/* Trabalha */}
      <div className="w-full flex flex-row items-center px-5 mb-2">
        <div className="min-w-[90px] h-[35px] rounded-[10px] bg-success flex items-center justify-center px-2 mr-2">
          <span className="text-background text-[16px] font-semibold text-center">Trabalha</span>
        </div>
        <div className="flex flex-row gap-4 ml-2">
          <RadioButtonWithLabel label="Sim" selected={trabalha === 0} onClick={() => setTrabalha(0)} />
          <RadioButtonWithLabel label="Não" selected={trabalha === 1} onClick={() => setTrabalha(1)} />
        </div>
      </div>

      {/* Renda */}
      <SimpleTextField
        nomeCampo="Renda"
        valorPreenchido={renda}
        onChange={setRenda}
      />

      {/* Botão adicionar integrante */}
      <button
        className="w-4/5 bg-main text-background font-semibold rounded-[10px] py-2 my-2 text-[16px] border-2 border-main hover:bg-success hover:text-main transition-colors"
        onClick={adicionarIntegrante}
      >
        Adicionar integrante
      </button>

      {/* Lista de componentes */}
      <div className="w-4/5 rounded-[10px] bg-success flex items-center justify-between px-4 py-3 cursor-pointer mb-2" onClick={() => setListaAberta(!listaAberta)}>
        <span className="text-background text-[16px] font-semibold">Componentes</span>
        <span className="text-greendark text-[16px] font-medium">Quantidade: {componentes.length}</span>
        <span className="text-black text-[22px] ml-2">{listaAberta ? "▲" : "▼"}</span>
      </div>
      {listaAberta && (
        <div className="w-4/5 mb-2">
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

      {/* Botões de navegação */}
      <div className="w-full flex flex-row justify-between px-5 mt-2">
        <button className="flex-1 bg-paragraph text-greendark font-semibold rounded-[10px] py-2 mr-2">
          &#x276E; Anterior
        </button>
        <button className="flex-1 bg-main text-background font-semibold rounded-[10px] py-2 ml-2">
          Proximo &#x276F;
        </button>
      </div>
    </div>
  );
} 