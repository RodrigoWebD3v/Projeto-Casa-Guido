'use client';

import { useState } from 'react';
import InputTextField from '@/components/TextField/InputTextField';
import SimpleTextField from '@/components/TextField/SimpleTextField';

export default function TelaLogin() {
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');
  const [erro, setErro] = useState('');
  const [carregando, setCarregando] = useState(false);

  const handleLogin = (e) => {
    e.preventDefault();
    setErro('');
    setCarregando(true);

    // Simulando autenticação simples
    if (email === 'admin@email.com' && senha === '123456') {
      alert('Login realizado com sucesso!');
      // Redirecionamento ou armazenamento de token pode ser feito aqui
    } else {
      setErro('E-mail ou senha inválidos');
    }

    setCarregando(false);
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-background text-greendark">
      <form
        onSubmit={handleLogin}
        className="bg-success p-8 rounded-xl shadow-md w-full max-w-md flex flex-col gap-4"
      >
        <h1 className="text-2xl font-bold text-center text-greendark mb-4">Login</h1>

        <InputTextField
          nomeCampo="E-mail"
          valorPreenchido={email}
          onChange={setEmail}
        />

        <SimpleTextField
          nomeCampo="Senha"
          placeholder="Digite sua senha"
          valorPreenchido={senha}
          onChange={setSenha}
        />

        {erro && <p className="text-red-600 text-sm text-center">{erro}</p>}

        <button
          type="submit"
          className="bg-success hover:bg-green-700 text-greendark py-2 px-4 rounded mt-2 transition"
        >
          {carregando ? 'Entrando...' : 'Entrar'}
        </button>
      </form>
    </div>
  );
}
