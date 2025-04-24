'use client';

import React from 'react';

export default function RadioButtonWithLabel({ label, selected, onClick }) {
  return (
    <div className="flex items-center justify-center gap-2">
      <span className="text-[14px] font-semibold text-greendark">{label}</span>
      <input
        type="radio"
        checked={selected}
        onChange={onClick}
        className="accent-success w-4 h-4 text-success cursor-pointer"
      />
    </div>
  );
}


/*
"use client";


import RadioButtonWithLabel from "@/components/Button/RadioButtonWithLabel";
import { useEffect, useState } from "react";


export default function Home() {
  const [selecionado, setSelecionado] = useState('A');

  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-24 bg-main">
      <RadioButtonWithLabel
        label="Opção A"
        selected={selecionado === 'A'}
        onClick={() => setSelecionado('A')}
      />
      <RadioButtonWithLabel
        label="Opção B"
        selected={selecionado === 'B'}
        onClick={() => setSelecionado('B')}
      />
    </main>
  );
  
}

*/