"use client"

import { Bar, BarChart, ResponsiveContainer, XAxis, YAxis, Tooltip } from "recharts"

const data = [
  {
    name: "01/03",
    total: 8,
  },
  {
    name: "05/03",
    total: 12,
  },
  {
    name: "10/03",
    total: 7,
  },
  {
    name: "15/03",
    total: 14,
  },
  {
    name: "20/03",
    total: 9,
  },
  {
    name: "25/03",
    total: 11,
  },
  {
    name: "30/03",
    total: 15,
  },
]

export function Overview() {
  return (
    <ResponsiveContainer width="100%" height={350}>
      <BarChart data={data}>
        <XAxis dataKey="name" stroke="#888888" fontSize={12} tickLine={false} axisLine={false} />
        <YAxis stroke="#888888" fontSize={12} tickLine={false} axisLine={false} tickFormatter={(value) => `${value}`} />
        <Tooltip
          cursor={{ fill: "rgba(171, 209, 198, 0.1)" }}
          contentStyle={{
            backgroundColor: "#e8e4e6",
            border: "1px solid #abd1c6",
            borderRadius: "4px",
          }}
        />
        <Bar dataKey="total" fill="#004643" radius={[4, 4, 0, 0]} />
      </BarChart>
    </ResponsiveContainer>
  )
}

