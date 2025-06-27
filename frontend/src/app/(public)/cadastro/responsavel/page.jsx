'use client';
import Sidebar from '@/components/Sidebar/sidebar';
import { useState } from 'react';
import Link from 'next/link';
import {
    Home, LayoutDashboard, User, UserPlus, ChevronLeft, Save, ChevronRight, ChevronUp,
    ChevronDown
} from 'lucide-react';
import SimpleTextField from '@/components/TextField/SimpleTextField';
import DatePickerInput from '@/components/DatePicker/DatePicker';
import MultiOptionRadioGroup from '@/components/Button/MultiOptionRadioGroup';

export default function ResponsavelForm() {
    const [nomeCompleto, setNomeCompleto] = useState('');
    const [cpf, setCpf] = useState('');
    const [cpfInvalido, setCpfInvalido] = useState(false);
    const [rg, setRg] = useState('');
    const [rgInvalido, setRgInvalido] = useState(false);
    const [naturalidade, setNaturalidade] = useState('');
    const [escolaridade, setEscolaridade] = useState('');
    const [dataNascimento, setDataNascimento] = useState('');
    const [celular, setCelular] = useState('');
    const [cartaoSus, setCartaoSus] = useState('');
    const [cartaoSusInvalido, setCartaoSusInvalido] = useState(false);
    const [salario, setSalario] = useState('');
    const [estadoCivil, setEstadoCivil] = useState(null);
    const [cadastroAberto, setCadastroAberto] = useState(true);

    // Funções de formatação e validação CPF
    function formatarCpf(valor) {
        const cpfNumeros = valor.replace(/\D/g, '').slice(0, 11);
        return cpfNumeros
            .replace(/^(\d{3})(\d)/, '$1.$2')
            .replace(/^(\d{3})\.(\d{3})(\d)/, '$1.$2.$3')
            .replace(/\.(\d{3})(\d)/, '.$1-$2');
    }

    function validarCpf(cpf) {
        const cpfLimpo = cpf.replace(/[^\d]+/g, '');
        if (cpfLimpo.length !== 11 || /^(\d)\1+$/.test(cpfLimpo)) return false;

        let soma = 0;
        for (let i = 0; i < 9; i++) soma += parseInt(cpfLimpo.charAt(i)) * (10 - i);
        let resto = (soma * 10) % 11;
        if (resto === 10 || resto === 11) resto = 0;
        if (resto !== parseInt(cpfLimpo.charAt(9))) return false;

        soma = 0;
        for (let i = 0; i < 10; i++) soma += parseInt(cpfLimpo.charAt(i)) * (11 - i);
        resto = (soma * 10) % 11;
        if (resto === 10 || resto === 11) resto = 0;
        return resto === parseInt(cpfLimpo.charAt(10));
    }

    function handleCpfChange(valor) {
        const formatado = formatarCpf(valor);
        setCpf(formatado);
        setCpfInvalido(formatado.length === 14 && !validarCpf(formatado));
    }

    // Funções formatação e validação RG
    function formatarRg(valor) {
        const numeros = valor.replace(/\D/g, '').slice(0, 9);
        return numeros
            .replace(/^(\d{2})(\d)/, '$1.$2')
            .replace(/^(\d{2})\.(\d{3})(\d)/, '$1.$2.$3')
            .replace(/\.(\d{3})(\d)/, '.$1-$2');
    }

    function validarRg(rg) {
        const rgLimpo = rg.replace(/\D/g, '');
        if (rgLimpo.length !== 9) return false;
        if (/^(\d)\1+$/.test(rgLimpo)) return false;
        return true;
    }

    function handleRgChange(valor) {
        const formatado = formatarRg(valor);
        setRg(formatado);
        setRgInvalido(formatado.length === 13 && !validarRg(formatado));
    }

    // Funções formatação e validação Cartão SUS
    function formatarCartaoSus(valor) {
        const numeros = valor.replace(/\D/g, '').slice(0, 15);
        return numeros
            .replace(/^(\d{3})(\d)/, '$1 $2')
            .replace(/^(\d{3}) (\d{4})(\d)/, '$1 $2 $3')
            .replace(/^(\d{3}) (\d{4}) (\d{4})(\d)/, '$1 $2 $3 $4');
    }

    function validarCartaoSus(cartao) {
        const susLimpo = cartao.replace(/\D/g, '');
        return susLimpo.length === 15;
    }
    function formatarCelular(valor) {
        const numeros = valor.replace(/\D/g, '').slice(0, 11);
        return numeros
            .replace(/^(\d{2})(\d)/, '($1) $2')
            .replace(/^(\(\d{2}\)) (\d{5})(\d)/, '$1 $2-$3');
    }

    function handleCelularChange(valor) {
        setCelular(formatarCelular(valor));
    }


    function handleCartaoSusChange(valor) {
        const formatado = formatarCartaoSus(valor);
        setCartaoSus(formatado);
        setCartaoSusInvalido(formatado.replace(/\D/g, '').length === 15 && !validarCartaoSus(formatado));
    }

    return (
        <div className="flex min-h-screen text-main bg-background">
            <Sidebar />
            <main className="flex-1 p-6 mt-4">
                <div className="max-w-10xl mx-auto">
                    <h1 className="flex items-center gap-2 text-2xl font-bold mb-6 text-center">
                        Cadastro <UserPlus size={18} />
                    </h1>

                    <div className="bg-offwhite rounded-lg shadow max-h-[calc(88vh-200px)] flex flex-col">
                        {/* Cabeçalho fixo */}
                        <div className="p-6 border-b border-graymedium bg-offwhite sticky top-0 z-10">
                            <h2 className="text-xl text-background font-semibold mb-2">6. Responsável</h2>
                            <h3 className="text-background">Informações do responsável:</h3>
                        </div>
                        <div className='overflow-y-auto p-6 space-y-6 pr-2 flex-1'>
                            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                                {/* Nome Completo */}
                                <div className="bg-offwhite p-4 rounded-lg shadow">
                                    <SimpleTextField nomeCampo="Nome Completo" valorPreenchido={nomeCompleto} onChange={setNomeCompleto} />
                                </div>

                                {/* CPF */}
                                <div className="bg-offwhite p-4 rounded-lg shadow">
                                    <SimpleTextField
                                        nomeCampo="CPF"
                                        valorPreenchido={cpf}
                                        onChange={handleCpfChange}
                                        className={cpfInvalido ? 'border border-red-500' : ''}
                                    />
                                    {cpfInvalido && (
                                        <p className="text-red-500 text-sm mt-1">CPF inválido</p>
                                    )}
                                </div>

                                {/* RG */}
                                <div className="bg-offwhite p-4 rounded-lg shadow">
                                    <SimpleTextField
                                        nomeCampo="RG"
                                        valorPreenchido={rg}
                                        onChange={handleRgChange}
                                        className={rgInvalido ? 'border border-red-500' : ''}
                                    />
                                    {rgInvalido && (
                                        <p className="text-red-500 text-sm mt-1">RG inválido</p>
                                    )}
                                </div>

                                {/* Naturalidade */}
                                <div className="bg-offwhite p-4 rounded-lg shadow">
                                    <SimpleTextField nomeCampo="Naturalidade" valorPreenchido={naturalidade} onChange={setNaturalidade} />
                                </div>

                                {/* Escolaridade */}
                                <div className="bg-offwhite p-4 rounded-lg shadow">
                                    <SimpleTextField nomeCampo="Escolaridade" valorPreenchido={escolaridade} onChange={setEscolaridade} />
                                </div>

                                {/* Data de Nascimento */}
                                <div className="bg-offwhite p-4 rounded-lg shadow">
                                    <DatePickerInput title="Data de Nascimento" value={dataNascimento} onChange={setDataNascimento} />
                                </div>

                                {/* Celular */}
                                <div className="bg-offwhite p-4 rounded-lg shadow">
                                    <SimpleTextField
                                        nomeCampo="Celular"
                                        valorPreenchido={celular}
                                        onChange={handleCelularChange}
                                        somenteNumero={true}
                                    />
                                </div>

                                {/* Cartão SUS */}
                                <div className="bg-offwhite p-4 rounded-lg shadow">
                                    <SimpleTextField
                                        nomeCampo="Cartão do SUS"
                                        valorPreenchido={cartaoSus}
                                        onChange={handleCartaoSusChange}
                                        className={cartaoSusInvalido ? 'border border-red-500' : ''}
                                    />
                                    {cartaoSusInvalido && (
                                        <p className="text-red-500 text-sm mt-1">Cartão SUS inválido</p>
                                    )}
                                </div>

                                {/* Salário */}
                                <div className="bg-offwhite p-4 rounded-lg shadow">
                                    <SimpleTextField nomeCampo="Salário" valorPreenchido={salario} onChange={setSalario} somenteNumero={true} />
                                </div>
                            </div>

                            {/* Estado Civil */}
                            <div className="bg-offwhite text-background p-4 rounded-lg shadow mt-6">
                                <MultiOptionRadioGroup
                                    labelTitulo="Estado Civil"
                                    selected={estadoCivil}
                                    onChange={setEstadoCivil}
                                    options={[['Solteiro', 0], ['Casado', 1], ['Viúvo', 2], ['Separado', 3]]}
                                    inline={true}
                                />
                            </div>
                        </div>
                    </div>
                </div>
                <div className="flex items-center mt-3 pt-6 border-t border-graymedium">
                    <Link href="/cadastro/radio" className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green transition text-sm">
                        <ChevronLeft size={18} /> Anterior
                    </Link>

                    <div className="flex-grow flex justify-center">
                        <button className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green text-sm transition">
                            <Save size={18} /> Salvar
                        </button>
                    </div>

                    <Link href="/cadastro/responsavel-opcional" className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green transition text-sm">
                        Próximo <ChevronRight size={18} />
                    </Link>
                </div>
            </main>
        </div>
    );
}
