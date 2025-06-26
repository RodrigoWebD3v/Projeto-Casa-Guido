'use client';

import { useState } from "react";
import MultiOptionRadioGroup from "@/components/Button/MultiOptionRadioGroup";
import SimpleTextField from "@/components/TextField/SimpleTextField";
import Link from 'next/link';
import {
  Home, LayoutDashboard, User, UserPlus, ChevronLeft, ChevronRight, Save, ChevronUp,
  ChevronDown
} from 'lucide-react';
import RadioButtonWithLabel from "@/components/Button/RadioButtonWithLabel";

export default function SituacaoHabitacional() {
  const [comoAdquiriu, setComoAdquiriu] = useState(null);
  const [compPropriedade, setCompPropriedade] = useState(null);
  const [area, setArea] = useState(null);
  const [numComodos, setNumComodos] = useState("");
  const [caracteristicas, setCaracteristicas] = useState(null);
  const [cadastroAberto, setCadastroAberto] = useState(true);

  return (
    <div className="flex min-h-screen w-full text-main bg-background">
      <aside
        className="w-64 bg-darkgray p-6 overflow-y-auto"
        style={{ boxShadow: '4px 0 8px rgba(0, 0, 0, 0.2)' }}
      >
        <h2 className="text-xl font-bold mb-6 flex items-center gap-2 text-main">
          <Home size={18} /> <span>Menu</span>
        </h2>

        <nav className="flex flex-col gap-4 text-sm">
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

          <div>
            <button
              onClick={() => setCadastroAberto(!cadastroAberto)}
              className="w-full flex items-center justify-between p-2 rounded border border-transparent text-main hover:border-greendark hover:bg-button hover:text-greendark transition"
            >
              <span className="flex items-center gap-2">
                <UserPlus size={18} /> Cadastro
              </span>
              {cadastroAberto ? <ChevronUp size={16} /> : <ChevronDown size={16} />}
            </button>

            {cadastroAberto && (
              <ul className="mt-2 flex flex-col gap-3">
                {[
                  ['1 - Identificação do Paciente', '/cadastro/identificacao-paciente'],
                  ['2 - Socioeconômico', '/cadastro/socio-economico'],
                  ['3 - Cirurgias', '/cadastro/cirurgias'],
                  ['4 - Quimioterapia', '/cadastro/quimio'],
                  ['5 - Radioterapia', '/cadastro/radio'],
                  ['6 - Responsável', '/cadastro/responsavel'],
                  ['7 - Responsável (Opcional)', '/cadastro/responsavel-opcional'],
                  ['8 - Composição Familiar', '/cadastro/composicao-familiar'],
                  ['9 - Histórico de Saúde', '/cadastro/historico-saude'],
                  ['10 - Situação Habitacional', '/cadastro/situacao-habitacional'],
                  ['11 - Endereço', '/cadastro/endereco'],
                  ['12 - Demais Dados', '/cadastro/demais-dados'],
                ].map(([label, href], idx) => (
                  <li
                    key={idx}
                    className="rounded border border-transparent text-main hover:border-greendark hover:bg-button hover:text-greendark transition"
                  >
                    <Link href={href} className="block px-2 py-1 hover:text-greendark">
                      {label}
                    </Link>
                  </li>
                ))}
              </ul>
            )}
          </div>
        </nav>
      </aside>

      <div className="min-h-screen flex-1">
        <main className="p-6 mt-4">
          <div className="max-w-10xl mx-auto">
            <h1 className="flex items-center gap-2 text-2xl font-bold mb-6 text-center">
              Cadastro <UserPlus size={18} />
            </h1>

            <div className="bg-offwhite rounded-lg shadow max-h-[calc(88vh-200px)] flex flex-col">
              {/* Cabeçalho fixo */}
              <div className="p-6 border-b border-graymedium bg-offwhite sticky top-0 z-10">
                <h2 className="text-xl text-darkgray font-semibold mb-2">10. Situação Habitacional</h2>
                <h3 className="text-darkgray">Informações habitacionais:</h3>
              </div>
              <div className="overflow-y-auto p-6 space-y-6 pr-2 flex-1">
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


            </div>
          </div>
          {/* Botões de navegação */}
          <div className="flex items-center mt-3 pt-6 border-t border-graymedium">
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
        </main>
      </div>
    </div>
  );
}
