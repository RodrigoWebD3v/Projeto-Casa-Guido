'use client';

import React from 'react';
import RadioButtonWithLabel from './RadioButtonWithLabel';
import RadioButtonWithFixedLabel from './RadioButtonWithFixedLabel'; // Ambos os componentes convertidos anteriormente

export default function MultiOptionRadioGroup({
  labelTitulo,
  options = [['Sim', 0], ['Não', 1]],
  selected,
  onChange = () => {},
}) {
  const metade = Math.ceil(options.length / 2);

  if (options.length > 2) {
    return (
      <div className="w-full flex flex-col items-center">
        <div className="w-full h-[60px] mb-2 rounded-[10px] bg-success flex items-center justify-center">
          <span className="text-background text-[16px] font-semibold text-center">
            {labelTitulo}
          </span>
        </div>

        <div className="flex flex-row justify-center items-start w-full gap-6">
          <div className="flex flex-col gap-2">
            {options.slice(0, metade).map(([label, value]) => (
              <RadioButtonWithFixedLabel
                key={value}
                label={label}
                selected={selected === value}
                onClick={() => onChange(value)}
              />
            ))}
          </div>

          <div className="h-full w-[1px] bg-black" />

          <div className="flex flex-col gap-2">
            {options.slice(metade).map(([label, value]) => (
              <RadioButtonWithFixedLabel
                key={value}
                label={label}
                selected={selected === value}
                onClick={() => onChange(value)}
              />
            ))}
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
        {options.map(([label, value]) => (
          <RadioButtonWithLabel
            key={value}
            label={label}
            selected={selected === value}
            onClick={() => onChange(value)}
          />
        ))}
      </div>
    </div>
  );
}


/*
"use client";


import MultiOptionRadioGroup from "@/components/Button/MultiOptionRadioGroup";

import { useEffect, useState } from "react";


export default function Home() {
  const [resposta, setResposta] = useState(null);

  

  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-24 bg-main">
        <MultiOptionRadioGroup
        labelTitulo="Você aceita?"
        selected={resposta}
        onChange={setResposta}
        options={[
          ['Sim', 0],
          ['Não', 1],
          ['Talvez', 2],
          ['Claro', 3],
        ]}
      />
    </main>
  );
  
}
*/