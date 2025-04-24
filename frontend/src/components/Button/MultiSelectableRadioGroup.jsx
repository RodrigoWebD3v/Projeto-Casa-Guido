'use client';

import React from 'react';
import RadioButtonWithFixedLabel from './RadioButtonWithFixedLabel';
import RadioButtonWithLabel from './RadioButtonWithLabel';

export default function MultiSelectableRadioGroup({
  labelTitulo,
  opcoesLista = ['Sim', 'Não'],
  opcoesSelecionadas = [],
  onClickListener = () => {},
}) {
  const metade = Math.ceil(opcoesLista.length / 2);

  const handleToggle = (index) => {
    onClickListener(index);
  };

  if (opcoesLista.length > 2) {
    return (
      <div className="w-full flex flex-col items-center">
        <div className="w-full h-[60px] mb-2 rounded-[10px] bg-success flex items-center justify-center">
          <span className="text-background text-[16px] font-semibold text-center">
            {labelTitulo}
          </span>
        </div>

        <div className="flex flex-row justify-center items-start w-full gap-6">
          <div className="flex flex-col gap-2">
            {opcoesLista.slice(0, metade).map((label, index) => (
              <RadioButtonWithFixedLabel
                key={index}
                label={label}
                selected={opcoesSelecionadas.includes(index)}
                onClick={() => handleToggle(index)}
              />
            ))}
          </div>

          <div className="h-full w-[1px] bg-black" />

          <div className="flex flex-col gap-2">
            {opcoesLista.slice(metade).map((label, i) => {
              const index = metade + i;
              return (
                <RadioButtonWithFixedLabel
                  key={index}
                  label={label}
                  selected={opcoesSelecionadas.includes(index)}
                  onClick={() => handleToggle(index)}
                />
              );
            })}
          </div>
        </div>
      </div>
    );
  }

  return (
    <div className="w-full flex flex-row items-center justify-center gap-4">
      <div className="min-w-[80px] min-h-[40px] px-4 py-2 rounded-[10px] bg-success flex items-center justify-center">
        <span className="text-background text-[16px] font-semibold text-center">
          {labelTitulo}
        </span>
      </div>

      <div className="flex gap-4">
        {opcoesLista.map((label, index) => (
          <RadioButtonWithLabel
            key={index}
            label={label}
            selected={opcoesSelecionadas.includes(index)}
            onClick={() => handleToggle(index)}
          />
        ))}
      </div>
    </div>
  );
}



/*
"use client";

import MultiSelectableRadioGroup from "@/components/Button/MultiSelectableRadioGroup";

import { useEffect, useState } from "react";


export default function Home() {
  const [selecionados, setSelecionados] = useState([]);

  const toggleSelecionado = (index) => {
    setSelecionados((prev) =>
      prev.includes(index)
        ? prev.filter((i) => i !== index)
        : [...prev, index]
    );
  };

  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-24 bg-main">
        <MultiSelectableRadioGroup
        labelTitulo="Selecione as opções:"
        opcoesLista={['Opção A', 'Opção B', 'Opção C', 'Opção D']}
        opcoesSelecionadas={selecionados}
        onClickListener={toggleSelecionado}
      />
    </main>
  );
  
}

*/