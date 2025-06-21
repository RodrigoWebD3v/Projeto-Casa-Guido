"use client";

import { useState } from "react";
import RadioButtonWithFixedLabel from "@/components/Button/RadioButtonWithFixedLabel";
import Link from 'next/link';
import { Home, LayoutDashboard, User, UserPlus, ChevronLeft, ChevronRight } from 'lucide-react';

const DOENCAS_ESQUERDA = [
  "Alcoolismo",
  "Deficiência Intelectual",
  "Deficiência Física",
  "Deficiência Auditiva",
  "Deficiência Visual",
  "Cardiopatia",
  "Asma",
  "Bronquite",
];
const DOENCAS_DIREITA = [
  "Hipert. arterial",
  "Toxoplasmose",
  "Drogadito",
  "Epilepsia",
  "Desnutrição",
  "Diabetes",
  "Câncer",
  "Outros",
];
const OPCOES_PROCURA = [
  "Hospital",
  "Unidade de Saúde",
  "Farmácia",
];

export default function HistoricoSaude() {
  const [doencasSelecionadas, setDoencasSelecionadas] = useState([]);
  const [procuraSelecionada, setProcuraSelecionada] = useState([]);

  const toggleDoenca = (nome) => {
    setDoencasSelecionadas((prev) =>
      prev.includes(nome)
        ? prev.filter((d) => d !== nome)
        : [...prev, nome]
    );
  };

  const toggleProcura = (opcao) => {
    setProcuraSelecionada((prev) =>
      prev.includes(opcao)
        ? prev.filter((p) => p !== opcao)
        : [...prev, opcao]
    );
  };

  return (
    <div className="flex min-h-screen w-full text-main bg-background">
      <aside
        className="w-44 bg-darkgray p-6"
        style={{ boxShadow: '4px 0 8px rgba(0, 0, 0, 0.2)' }}
      >
        <h2 className="text-xl font-bold mb-6 flex items-center gap-2 text-main">
          <Home size={18} /> <span>Menu</span>
        </h2>
        <nav className="flex flex-col gap-4">
          <Link
            href="/dashboard"
            className="flex items-center gap-2 p-2 rounded border border-transparent text-main hover:border-greendark hover:bg-button hover:text-greendark transition"
          >
            <LayoutDashboard size={18} /> <span>Dashboard</span>
          </Link>
          <Link
            href="/listagem-pacientes"
            className="flex items-center gap-2 p-2 rounded border border-transparent text-main hover:border-greendark hover:bg-button hover:text-greendark transition"
          >
            <User size={18} /> <span>Pacientes</span>
          </Link>
        </nav>
      </aside>
      <div className="bg-background min-h-screen flex-1">
        <main className="p-6 mt-4">
          <div className="max-w-10xl mx-auto">
            <h1 className="flex items-center gap-2 text-2xl font-bold mb-6 text-center">Cadastro <UserPlus size={18} /></h1>
            <div className="bg-offwhite p-6 rounded-lg shadow">
              <h2 className="text-xl text-darkgray font-semibold mb-4">10. Histórico de Saúde</h2>
              <h3 className="text-darkgray mb-8">Informações do histórico de saúde:</h3>
              {/* Doenças existentes na família */}
              <div className="w-full h-[45px] mb-2 rounded-[10px] bg-success flex items-center justify-center">
                <span className="text-background text-[16px] font-semibold text-center">
                  Doenças existentes na família
                </span>
              </div>
              <div className="w-full flex flex-row gap-4 mb-2">
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
              <div className="w-full h-[45px] my-2 rounded-[10px] bg-success flex items-center justify-center">
                <span className="text-background text-[16px] font-semibold text-center">
                  Em caso de doença, procura
                </span>
              </div>
              <div className="w-full flex flex-col gap-2 mb-2">
                {OPCOES_PROCURA.map((opcao) => (
                  <RadioButtonWithFixedLabel
                    key={opcao}
                    label={opcao}
                    selected={procuraSelecionada.includes(opcao)}
                    onClick={() => toggleProcura(opcao)}
                  />
                ))}
              </div>
              {/* Botões de navegação */}
              <div className="flex items-center mt-8 pt-6 border-t border-graymedium">
                <button className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green text-sm transition">
                  <ChevronLeft size={18} /> Anterior
                </button>
                <div className="flex-grow flex justify-center"></div>
                <button className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green text-sm transition">
                  Próximo <ChevronRight size={18} />
                </button>
              </div>
            </div>
          </div>
        </main>
      </div>
    </div>
  );
} 