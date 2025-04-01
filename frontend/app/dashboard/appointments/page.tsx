"use client"

import { useState } from "react"
import { Button } from "@/components/ui/button"
import { Calendar } from "@/components/ui/calendar"
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select"
import { CalendarPlus, ChevronLeft, ChevronRight } from "lucide-react"
import { AppointmentList } from "@/components/appointment-list"
import Link from "next/link"
import { format, addMonths, subMonths } from "date-fns"
import { ptBR } from "date-fns/locale"

export default function AppointmentsPage() {
  const [date, setDate] = useState<Date>(new Date())
  const [view, setView] = useState<"day" | "week" | "month">("day")
  const [month, setMonth] = useState<Date>(new Date())

  const handlePreviousMonth = () => {
    setMonth((prev) => subMonths(prev, 1))
  }

  const handleNextMonth = () => {
    setMonth((prev) => addMonths(prev, 1))
  }

  return (
    <div className="space-y-6">
      <div className="flex items-center justify-between">
        <h1 className="text-3xl font-bold tracking-tight text-primary">Agendamentos</h1>
        <Button className="bg-secondary text-secondary-foreground hover:bg-secondary/90" asChild>
          <Link href="/dashboard/appointments/new">
            <CalendarPlus className="mr-2 h-4 w-4" />
            Novo Agendamento
          </Link>
        </Button>
      </div>

      <div className="grid gap-6 md:grid-cols-7">
        <Card className="md:col-span-2">
          <CardHeader>
            <div className="flex items-center justify-between">
              <CardTitle>Calendário</CardTitle>
              <div className="flex items-center">
                <Button variant="outline" size="icon" onClick={handlePreviousMonth}>
                  <ChevronLeft className="h-4 w-4" />
                </Button>
                <div className="w-28 text-center">{format(month, "MMMM yyyy", { locale: ptBR })}</div>
                <Button variant="outline" size="icon" onClick={handleNextMonth}>
                  <ChevronRight className="h-4 w-4" />
                </Button>
              </div>
            </div>
            <CardDescription>Selecione uma data para ver os agendamentos</CardDescription>
          </CardHeader>
          <CardContent>
            <Calendar
              mode="single"
              selected={date}
              onSelect={(date) => date && setDate(date)}
              month={month}
              onMonthChange={setMonth}
              className="rounded-md border"
              locale={ptBR}
            />
          </CardContent>
        </Card>

        <Card className="md:col-span-5">
          <CardHeader>
            <div className="flex items-center justify-between">
              <CardTitle>Agendamentos: {format(date, "dd 'de' MMMM 'de' yyyy", { locale: ptBR })}</CardTitle>
              <Select value={view} onValueChange={(value) => setView(value as "day" | "week" | "month")}>
                <SelectTrigger className="w-[180px]">
                  <SelectValue placeholder="Visualização" />
                </SelectTrigger>
                <SelectContent>
                  <SelectItem value="day">Dia</SelectItem>
                  <SelectItem value="week">Semana</SelectItem>
                  <SelectItem value="month">Mês</SelectItem>
                </SelectContent>
              </Select>
            </div>
            <CardDescription>
              {view === "day"
                ? "Visualizando agendamentos do dia"
                : view === "week"
                  ? "Visualizando agendamentos da semana"
                  : "Visualizando agendamentos do mês"}
            </CardDescription>
          </CardHeader>
          <CardContent>
            <AppointmentList date={date} view={view} />
          </CardContent>
        </Card>
      </div>
    </div>
  )
}

