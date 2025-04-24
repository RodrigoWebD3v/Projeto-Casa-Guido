'use client';

import React from 'react';

export default function RadioButtonWithFixedLabel({ label, selected, onClick }) {
  return (
    <div className="flex items-center justify-between w-full">
      <span className="w-[120px] text-[14px] font-semibold text-greendark text-start">
        {label}
      </span>
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


import RadioButtonWithFixedLabel from "@/components/Button/RadioButtonWithFixedLabel";
import RadioButtonWithLabel from "@/components/Button/RadioButtonWithLabel";
import { useEffect, useState } from "react";


export default function Home() {
  const [escolha, setEscolha] = useState('1');

  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-24 bg-main">
      <RadioButtonWithFixedLabel
        label="Opção 1"
        selected={escolha === '1'}
        onClick={() => setEscolha('1')}
      />
      <RadioButtonWithFixedLabel
        label="Opção 2"
        selected={escolha === '2'}
        onClick={() => setEscolha('2')}
      />
    </main>
  );
  
}

*/