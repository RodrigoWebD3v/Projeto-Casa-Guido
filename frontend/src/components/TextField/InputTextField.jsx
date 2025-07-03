import React from 'react';

export default function InputTextField({
  nomeCampo = '',
  valorPreenchido = '',
  onChange = () => { },
  type = 'text',          // tipo do input, padrão texto
  className = '',         // permite passar classes CSS extras
  placeholder = '',       // opcional, caso queira diferenciar do nomeCampo
}) {
  return (
    <div className="w-full relative">
      <input
        type={type}
        value={valorPreenchido}
        onChange={(e) => onChange(e.target.value)}
        placeholder={placeholder || nomeCampo}
        className={`w-full text-[18px] text-greendark placeholder-background border-b-2 border-paragraph focus:outline-none focus:border-paragraph py-2 px-1 rounded-sm ${className}`}
      />
    </div>
  );
}
