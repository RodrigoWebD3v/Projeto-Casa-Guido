"use client";

import { useState } from "react";
import MultiOptionRadioGroup from "@/components/Button/MultiOptionRadioGroup";
import SimpleTextField from "@/components/TextField/SimpleTextField";

export default function SituacaoHabitacional() {
  const [comoAdquiriu, setComoAdquiriu] = useState(null);
  const [compPropriedade, setCompPropriedade] = useState(null);
  const [area, setArea] = useState(null);
  const [numComodos, setNumComodos] = useState("");
  const [caracteristicas, setCaracteristicas] = useState(null);

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
        <span className="block text-[18px] font-bold text-greendark">11. Situacao Habitacional</span>
        <span className="block text-[13px] text-greendark">Informações habitacionais</span>
      </div>

      {/* Como adquiriu a casa */}
      <MultiOptionRadioGroup
        labelTitulo="Como adquiriu a casa"
        options={[
          ["Doacao", 0], ["Ocupacao", 1], ["Comprada", 2],
          ["Alugada", 3], ["Cedida", 4]
        ]}
        selected={comoAdquiriu}
        onChange={setComoAdquiriu}
      />

      {/* Comp. Propriedade */}
      <MultiOptionRadioGroup
        labelTitulo="Comp. Propriedade"
        options={[["Sim", 0], ["Não", 1]]}
        selected={compPropriedade}
        onChange={setCompPropriedade}
      />

      {/* Área */}
      <MultiOptionRadioGroup
        labelTitulo="Área"
        options={[["Pública", 0], ["Particular", 1]]}
        selected={area}
        onChange={setArea}
      />

      {/* Número de Cômodos */}
      <SimpleTextField
        nomeCampo="Numero de Comodos"
        valorPreenchido={numComodos}
        somenteNumero={true}
        onChange={setNumComodos}
      />

      {/* Características */}
      <MultiOptionRadioGroup
        labelTitulo="Características"
        options={[["Alvenaria", 0], ["Madeira", 1], ["Mista", 2]]}
        selected={caracteristicas}
        onChange={setCaracteristicas}
      />

      {/* Eletrodoméstico (apenas título visual, sem opções) */}
      <div className="w-4/5 h-[45px] my-2 rounded-[10px] bg-success flex items-center justify-center">
        <span className="text-background text-[16px] font-semibold text-center">
          Eletrodomestico
        </span>
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