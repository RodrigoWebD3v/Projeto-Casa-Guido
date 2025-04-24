'use client';

import React from 'react';

export default function SimpleTextField({
  nomeCampo = '',
  valorPreenchido = '',
  placeholder = '',
  somenteNumero = false,
  singleLine = true,
  onChange = () => {},
}) {
  const handleChange = (e) => {
    const valor = e.target.value;
    if (somenteNumero) {
      const apenasNumeros = valor.replace(/\D/g, '');
      onChange(apenasNumeros);
    } else {
      onChange(valor);
    }
  };

  return (
    <div className="w-full px-5 mb-4">
      <label className="block text-[14px] font-medium text-greendark mb-1">
        {nomeCampo}
      </label>
      <div className="w-full border border-paragraph rounded-[8px] min-h-[45px] max-h-[300px] px-3 py-2">
        <input
          type={somenteNumero ? 'tel' : 'text'}
          value={valorPreenchido}
          onChange={handleChange}
          placeholder={placeholder}
          className={`w-full text-[16px] text-greendark bg-transparent outline-none placeholder-background ${
            singleLine ? 'whitespace-nowrap overflow-hidden' : ''
          }`}
        />
      </div>
    </div>
  );
}

/*
"use client";

import SimpleTextField from "@/components/TextField/SimpleTextField";

import { useEffect, useState } from "react";


export default function Home() {
  const [valor, setValor] = useState('');

  
  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-24 bg-main">
          <SimpleTextField
        nomeCampo="Telefone"
        placeholder="Digite seu telefone"
        valorPreenchido={valor}
        somenteNumero={true}
        onChange={setValor}
      />
    </main>
  );
  
}
 */