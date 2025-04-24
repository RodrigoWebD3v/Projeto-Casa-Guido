'use client';

import { useState } from 'react';
import { Check } from 'lucide-react'; // ou qualquer outro ícone
import clsx from 'clsx'; // opcional para condicional de classes (estilo)

export default function DropDownMenu({ options = [], onSelected }) {
  const [isOpen, setIsOpen] = useState(false);
  const [selected, setSelected] = useState(options[0] || { nome: 'Selecione', id: '' });

  const handleSelect = (item) => {
    setSelected(item);
    setIsOpen(false);
    onSelected(item);
  };

  return (
    <div className="relative w-4/5 mx-auto">
      <button
        onClick={() => setIsOpen(!isOpen)}
        className="w-full bg-background text-main px-4 py-2 border border-background rounded-t-md flex justify-between items-center"
      >
        <span>{selected.nome}</span>
        <svg
          className={`w-4 h-4 transition-transform ${isOpen ? 'rotate-180' : ''}`}
          fill="none"
          stroke="currentColor"
          strokeWidth="2"
          viewBox="0 0 24 24"
        >
          <path d="M19 9l-7 7-7-7" />
        </svg>
      </button>

      {isOpen && (
        <ul className="absolute z-10 w-full bg-background border border-t-0 border-background rounded-b-md max-h-60 overflow-y-auto">
          {options.map((item) => (
            <li
              key={item.id}
              className={clsx(
                'px-4 py-2 cursor-pointer flex items-center justify-between hover:bg-main hover:text-background transition-colors',
                selected.id === item.id && 'font-semibold'
              )}
              onClick={() => handleSelect(item)}
            >
              <span>{item.nome}</span>
              {selected.id === item.id && <Check className="w-4 h-4 text-main" />}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}


/*
"use client";


import DropDownMenu from "@/components/DropDownMenu/DropDrownMenu";
import { useEffect, useState } from "react";

const options = [
  { id: '1', nome: 'Opção 1' },
  { id: '2', nome: 'Opção 2' },
  { id: '3', nome: 'Opção 3' },
];

export default function Home() {
  const handleSelecionado = (item) => {
    console.log('Selecionado:', item);
  };


  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-24 bg-main">
       <DropDownMenu options={options} onSelected={handleSelecionado} />
    </main>
  );
  
}
*/