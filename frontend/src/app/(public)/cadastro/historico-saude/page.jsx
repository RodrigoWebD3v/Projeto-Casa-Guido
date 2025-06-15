"use client";

import { useState } from "react";
import RadioButtonWithFixedLabel from "@/components/Button/RadioButtonWithFixedLabel";

const DOENCAS_ESQUERDA = [
  "Alcoolismo",
  "Deficiencia Intelectual",
  "Deficiencia Fisica",
  "Deficiencia Auditiva",
  "Deficiencia Visual",
  "Cardiopatia",
  "Asma",
  "Bronquite",
];
const DOENCAS_DIREITA = [
  "Hipert. arterial",
  "Toxoplasmose",
  "Drogadito",
  "Epilepsia",
  "Desnutricao",
  "Diabete",
  "Cancer",
  "Outros",
];
const OPCOES_PROCURA = [
  "Hospital",
  "Unidade de Saúde",
  "Farmacia",
];

export default function HistoricoSaude() {
  const [doencasSelecionadas, setDoencasSelecionadas] = useState([]);
  const [procura, setProcura] = useState(null);

  const toggleDoenca = (nome) => {
    setDoencasSelecionadas((prev) =>
      prev.includes(nome)
        ? prev.filter((d) => d !== nome)
        : [...prev, nome]
    );
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
        <span className="block text-[18px] font-bold text-greendark">10. Histórico de Saúde</span>
        <span className="block text-[13px] text-greendark">Informações do historico de saude</span>
      </div>

      {/* Doenças existentes na família */}
      <div className="w-4/5 h-[45px] mb-2 rounded-[10px] bg-success flex items-center justify-center">
        <span className="text-background text-[16px] font-semibold text-center">
          Doencas existentes na familia
        </span>
      </div>
      <div className="w-4/5 flex flex-row gap-4 mb-2">
        <div className="flex flex-col gap-2 flex-1">
          {DOENCAS_ESQUERDA.map((nome, idx) => (
            <RadioButtonWithFixedLabel
              key={nome}
              label={nome}
              selected={doencasSelecionadas.includes(nome)}
              onClick={() => toggleDoenca(nome)}
            />
          ))}
        </div>
        <div className="w-[1px] bg-greendark" />
        <div className="flex flex-col gap-2 flex-1">
          {DOENCAS_DIREITA.map((nome, idx) => (
            <RadioButtonWithFixedLabel
              key={nome}
              label={nome}
              selected={doencasSelecionadas.includes(nome)}
              onClick={() => toggleDoenca(nome)}
            />
          ))}
        </div>
      </div>

      {/* Em caso de doença, procura */}
      <div className="w-4/5 h-[45px] my-2 rounded-[10px] bg-success flex items-center justify-center">
        <span className="text-background text-[16px] font-semibold text-center">
          Em caso de doenca, procura
        </span>
      </div>
      <div className="w-4/5 flex flex-col gap-2 mb-2">
        {OPCOES_PROCURA.map((opcao, idx) => (
          <div key={opcao} className="flex flex-row items-center justify-between">
            <span className="text-greendark text-[15px] font-medium">{opcao}</span>
            <input
              type="radio"
              checked={procura === idx}
              onChange={() => setProcura(idx)}
              className="accent-success w-4 h-4 text-success cursor-pointer"
            />
          </div>
        ))}
      </div>

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