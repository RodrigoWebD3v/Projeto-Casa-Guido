'use client';


import MultiOptionRadioGroup from '@/components/Button/MultiOptionRadioGroup';
import MultiSelectableRadioGroup from '@/components/Button/MultiSelectableRadioGroup';
import RadioButtonWithFixedLabel from '@/components/Button/RadioButtonWithFixedLabel';
import RadioButtonWithLabel from '@/components/Button/RadioButtonWithLabel';
import DatePickerInput from '@/components/DatePicker/DatePicker';
import DropDownMenu from '@/components/DropDownMenu/DropDrownMenu';
import InputTextField from '@/components/TextField/InputTextField';
import SimpleTextField from '@/components/TextField/SimpleTextField';
import { useState } from 'react';



export default function ComponentGallery() {
  const [search, setSearch] = useState('');
  const [date, setDate] = useState('');
  const [dropdownSelected, setDropdownSelected] = useState({ id: '', nome: 'Selecione' });
  const [radio1, setRadio1] = useState(false);
  const [radio2, setRadio2] = useState(false);
  const [multiOption, setMultiOption] = useState(null);
  const [multiSelect, setMultiSelect] = useState([]);
  const [textValue, setTextValue] = useState('');
  const [simpleText, setSimpleText] = useState('');

  const dropOptions = [
    { id: '1', nome: 'Opção B' },
    { id: '2', nome: 'Opção C' },
  ];

  const multiOptions = [
    ['Sim', 0],
    ['Não', 1],
    ['Talvez', 2],
    ['Claro', 3],
  ];

  const multiselectOptions = ['Maçã', 'Banana', 'Pêra', 'Abacaxi'];

  const items = [
    // {
    //     title: 'SearchBox',
    //     component: <SearchBox value={search} onValueChange={setSearch} />,
    //   },
    {
      title: 'DatePickerInput',
      component: <DatePickerInput title="Data de Nascimento" value={date} onChange={setDate} />,
    },
    {
      title: 'DropDownMenu',
      component: <DropDownMenu options={dropOptions} onSelected={setDropdownSelected} />,
    },
    {
      title: 'RadioButtonWithLabel',
      component: (
        <RadioButtonWithLabel
          label="Aceita os termos?"
          selected={radio1}
          onClick={() => setRadio1(!radio1)}
        />
      ),
    },
    {
      title: 'RadioButtonWithFixedLabel',
      component: (
        <RadioButtonWithFixedLabel
          label="Você é humano?"
          selected={radio2}
          onClick={() => setRadio2(!radio2)}
        />
      ),
    },
    {
      title: 'MultiOptionRadioGroup',
      component: (
        <MultiOptionRadioGroup
          labelTitulo="Qual sua escolha?"
          selected={multiOption}
          onChange={setMultiOption}
          options={multiOptions}
        />
      ),
    },
    {
      title: 'MultiSelectableRadioGroup',
      component: (
        <MultiSelectableRadioGroup
          labelTitulo="Quais frutas você gosta?"
          opcoesLista={multiselectOptions}
          opcoesSelecionadas={multiSelect}
          onClickListener={(index) =>
            setMultiSelect((prev) =>
              prev.includes(index)
                ? prev.filter((i) => i !== index)
                : [...prev, index]
            )
          }
        />
      ),
    },
    {
      title: 'InputTextField',
      component: (
        <InputTextField
          nomeCampo="Digite seu nome"
          valorPreenchido={textValue}
          onChange={setTextValue}
        />
      ),
    },
    {
      title: 'SimpleTextField',
      component: (
        <SimpleTextField
          nomeCampo="Telefone"
          placeholder="Digite seu telefone"
          valorPreenchido={simpleText}
          somenteNumero={true}
          onChange={setSimpleText}
        />
      ),
    },
  ];

  return (
    <div className="p- bg-background text-main min-h-screen">
      <h1 className="text-2xl font-bold mb-8">Catálogo de Componentes</h1>
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-8">
        {items.map((item, idx) => (
          <div
            key={idx}
            className="bg-main text-greendark p-4 rounded-xl shadow-md flex flex-col gap-4"
          >
            <h2 className="text-lg font-semibold">{item.title}</h2>
            <div>{item.component}</div>
          </div>
        ))}
      </div>
    </div>
  );
}