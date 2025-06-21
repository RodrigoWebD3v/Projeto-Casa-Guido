"use client";

import { useState } from "react";
import MultiOptionRadioGroup from "@/components/Button/MultiOptionRadioGroup";
import SimpleTextField from "@/components/TextField/SimpleTextField";
import Link from 'next/link';
import { Home, LayoutDashboard, User, UserPlus, ChevronLeft, ChevronRight } from 'lucide-react';

export default function SituacaoHabitacional() {
  const [comoAdquiriu, setComoAdquiriu] = useState(null);
  const [compPropriedade, setCompPropriedade] = useState(null);
  const [area, setArea] = useState(null);
  const [numComodos, setNumComodos] = useState("");
  const [caracteristicas, setCaracteristicas] = useState(null);
  const [eletrodomesticos, setEletrodomesticos] = useState("");

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
              <h2 className="text-xl text-darkgray font-semibold mb-4">11. Situação Habitacional</h2>
              <h3 className="text-darkgray mb-8">Informações habitacionais:</h3>

              <div className="space-y-6">
                {/* Como adquiriu a casa */}
                <div className="bg-offwhite p-6 rounded-lg shadow">
                  <MultiOptionRadioGroup
                    labelTitulo="Como adquiriu a casa"
                    options={[
                      ["Doação", 0], ["Ocupação", 1], ["Comprada", 2],
                      ["Alugada", 3], ["Cedida", 4]
                    ]}
                    selected={comoAdquiriu}
                    onChange={setComoAdquiriu}
                  />
                </div>

                {/* Comp. Propriedade */}
                <div className="bg-offwhite p-6 rounded-lg shadow">
                  <MultiOptionRadioGroup
                    labelTitulo="Comp. Propriedade"
                    options={[["Sim", 0], ["Não", 1]]}
                    selected={compPropriedade}
                    onChange={setCompPropriedade}
                  />
                </div>

                {/* Área */}
                <div className="bg-offwhite p-6 rounded-lg shadow">
                  <MultiOptionRadioGroup
                    labelTitulo="Área"
                    options={[["Pública", 0], ["Particular", 1]]}
                    selected={area}
                    onChange={setArea}
                  />
                </div>

                {/* Número de Cômodos */}
                <div className="bg-offwhite p-6 rounded-lg shadow">
                  <SimpleTextField
                    nomeCampo="Número de Cômodos"
                    valorPreenchido={numComodos}
                    somenteNumero={true}
                    onChange={setNumComodos}
                  />
                </div>

                {/* Características */}
                <div className="bg-offwhite p-6 rounded-lg shadow">
                  <MultiOptionRadioGroup
                    labelTitulo="Características"
                    options={[["Alvenaria", 0], ["Madeira", 1], ["Mista", 2]]}
                    selected={caracteristicas}
                    onChange={setCaracteristicas}
                  />
                </div>

                {/* Eletrodoméstico */}
                <div className="bg-offwhite p-6 rounded-lg shadow">
                  <div className="w-full h-[45px] mb-4 rounded-[10px] bg-success flex items-center justify-center">
                    <span className="text-background text-[16px] font-semibold text-center">
                      Eletrodoméstico
                    </span>
                  </div>
                  <SimpleTextField
                    nomeCampo="Eletrodomésticos"
                    valorPreenchido={eletrodomesticos}
                    onChange={setEletrodomesticos}
                    placeholder="Digite os eletrodomésticos disponíveis"
                  />
                </div>
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