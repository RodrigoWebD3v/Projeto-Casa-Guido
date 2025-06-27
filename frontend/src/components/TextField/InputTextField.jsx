'use client';

import React from 'react';

export default function InputTextField({
  nomeCampo = '',
  valorPreenchido = '',
  onChange = () => { },
}) {
  return (
    <div className="w-full">
      <input
        type="text"
        value={valorPreenchido}
        onChange={(e) => onChange(e.target.value)}
        placeholder={nomeCampo}
        className="w-full text-[18px] text-greendark placeholder-background border-b-2 border-paragraph focus:outline-none focus:border-paragraph py-2 px-1 rounded-sm"
      />
    </div>
  );
}


/*
"use client";

import InputTextField from "@/components/TextField/InputTextField";

import { useEffect, useState } from "react";


export default function Home() {
  const [texto, setTexto] = useState('');

  
  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-24 bg-main">
        <InputTextField
        nomeCampo="Digite aqui..."
        valorPreenchido={texto}
        onChange={setTexto}
      />
    </main>
  );
  
}
*/