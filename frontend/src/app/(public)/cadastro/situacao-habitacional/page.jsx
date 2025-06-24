'use client';

import { useState } from "react";
import MultiOptionRadioGroup from "@/components/Button/MultiOptionRadioGroup";
import SimpleTextField from "@/components/TextField/SimpleTextField";
import Link from 'next/link';
import { Home, LayoutDashboard, User, UserPlus, ChevronLeft, ChevronRight, Save } from 'lucide-react';
import RadioButtonWithLabel from "@/components/Button/RadioButtonWithLabel";

export default function SituacaoHabitacional() {
  const [comoAdquiriu, setComoAdquiriu] = useState(null);
  const [compPropriedade, setCompPropriedade] = useState(null);
  const [area, setArea] = useState(null);
  const [numComodos, setNumComodos] = useState("");
  const [caracteristicas, setCaracteristicas] = useState(null);

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

      <div className="min-h-screen flex-1">
        <main className="p-6 mt-4">
          <div className="max-w-10xl mx-auto">
            <h1 className="flex items-center gap-2 text-2xl font-bold mb-6 text-center">
              Cadastro <UserPlus size={18} />
            </h1>

            <div className="bg-offwhite p-6 rounded-lg shadow">
              <h2 className="text-xl text-darkgray font-semibold mb-4">10. Situação Habitacional</h2>
              <h3 className="text-darkgray mb-8">Informações habitacionais:</h3>

              <div className="space-y-6">
                {/* Linha 1: Como adquiriu + Características */}
                <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
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

                  <div className="bg-offwhite p-6 rounded-lg shadow">
                    <MultiOptionRadioGroup
                      labelTitulo="Características"
                      options={[["Alvenaria", 0], ["Madeira", 1], ["Mista", 2]]}
                      selected={caracteristicas}
                      onChange={setCaracteristicas}
                    />
                  </div>
                </div>

                {/* Linha 2: Comp. Propriedade + Área */}
                <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
                  <div className="bg-offwhite p-6 rounded-lg shadow">
                    <div className="w-full h-[60px] mb-2 rounded-[10px] bg-success flex items-center justify-center">
                      <span className="text-background text-[16px] font-semibold text-center">
                        Comp. Propriedade
                      </span>
                    </div>
                    <div className="flex justify-center gap-4">
                      <RadioButtonWithLabel
                        label="Sim"
                        selected={compPropriedade === 0}
                        onClick={() => setCompPropriedade(0)}
                      />
                      <RadioButtonWithLabel
                        label="Não"
                        selected={compPropriedade === 1}
                        onClick={() => setCompPropriedade(1)}
                      />
                    </div>
                  </div>

                  <div className="bg-offwhite p-6 rounded-lg shadow">
                    <div className="w-full h-[60px] mb-2 rounded-[10px] bg-success flex items-center justify-center">
                      <span className="text-background text-[16px] font-semibold text-center">
                        Área
                      </span>
                    </div>
                    <div className="flex justify-center gap-4">
                      <RadioButtonWithLabel
                        label="Pública"
                        selected={area === 0}
                        onClick={() => setArea(0)}
                      />
                      <RadioButtonWithLabel
                        label="Particular"
                        selected={area === 1}
                        onClick={() => setArea(1)}
                      />
                    </div>
                  </div>
                </div>

                {/* Linha 3: Número de cômodos (sozinho) */}
                <div className="bg-offwhite p-6 rounded-lg shadow">
                  <SimpleTextField
                    nomeCampo="Número de Cômodos"
                    valorPreenchido={numComodos}
                    somenteNumero={true}
                    onChange={setNumComodos}
                  />
                </div>
              </div>

              {/* Botões de navegação */}
              <div className="flex items-center mt-8 pt-6 border-t border-graymedium">
                <Link href="/cadastro/historico-saude" className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green transition text-sm">
                  <ChevronLeft size={18} /> Anterior
                </Link>

                <div className="flex-grow flex justify-center">
                  <button className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green text-sm transition">
                    <Save size={18} /> Salvar
                  </button>
                </div>

                <Link href="/cadastro/endereco" className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green transition text-sm">
                  Próximo <ChevronRight size={18} />
                </Link>
              </div>
            </div>
          </div>
        </main>
      </div>
    </div>
  );
}
