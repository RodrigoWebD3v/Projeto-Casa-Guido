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
