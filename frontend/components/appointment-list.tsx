"use client"

import { Badge } from "@/components/ui/badge"
import { Card } from "@/components/ui/card"
import { format, startOfWeek, endOfWeek, eachDayOfInterval, isSameDay } from "date-fns"
import { ptBR } from "date-fns/locale"

type AppointmentListProps = {
  date: Date
  view: "day" | "week" | "month"
}

// Dados de exemplo
const appointments = [
  {
    id: "1",
    patientName: "Ana Silva",
    time: "09:00",
    type: "Consulta Inicial",
    status: "Confirmado",
    date: new Date(2025, 2, 31), // 31/03/2025
  },
  {
    id: "2",
    patientName: "Carlos Oliveira",
    time: "10:30",
    type: "Retorno",
    status: "Pendente",
    date: new Date(2025, 2, 31), // 31/03/2025
  },
  {
    id: "3",
    patientName: "Mariana Santos",
    time: "14:00",
    type: "Exame",
    status: "Confirmado",
    date: new Date(2025, 2, 31), // 31/03/2025
  },
  {
    id: "4",
    patientName: "Pedro Costa",
    time: "15:30",
    type: "Procedimento",
    status: "Confirmado",
    date: new Date(2025, 3, 1), // 01/04/2025
  },
  {
    id: "5",
    patientName: "Juliana Lima",
    time: "17:00",
    type: "Retorno",
    status: "Pendente",
    date: new Date(2025, 3, 1), // 01/04/2025
  },
  {
    id: "6",
    patientName: "Roberto Alves",
    time: "09:30",
    type: "Consulta Inicial",
    status: "Confirmado",
    date: new Date(2025, 3, 2), // 02/04/2025
  },
  {
    id: "7",
    patientName: "Fernanda Dias",
    time: "11:00",
    type: "Exame",
    status: "Confirmado",
    date: new Date(2025, 3, 2), // 02/04/2025
  },
]

export function AppointmentList({ date, view }: AppointmentListProps) {
  // Filtra os agendamentos com base na visualização selecionada
  const getFilteredAppointments = () => {
    if (view === "day") {
      return appointments.filter((appointment) => isSameDay(appointment.date, date))
    } else if (view === "week") {
      const start = startOfWeek(date, { locale: ptBR })
      const end = endOfWeek(date, { locale: ptBR })
      return appointments.filter((appointment) => appointment.date >= start && appointment.date <= end)
    } else {
      // Implementação simplificada para visualização mensal
      // Em um app real, você filtraria por mês
      return appointments
    }
  }

  const filteredAppointments = getFilteredAppointments()

  // Para visualização semanal, agrupa por dia
  const getDaysInWeek = () => {
    const start = startOfWeek(date, { locale: ptBR })
    const end = endOfWeek(date, { locale: ptBR })
    return eachDayOfInterval({ start, end })
  }

  if (view === "week") {
    const daysInWeek = getDaysInWeek()

    return (
      <div className="space-y-6">
        {daysInWeek.map((day) => {
          const dayAppointments = appointments.filter((appointment) => isSameDay(appointment.date, day))

          return (
            <div key={day.toString()} className="space-y-2">
              <h3 className="font-medium">{format(day, "EEEE, dd 'de' MMMM", { locale: ptBR })}</h3>

              {dayAppointments.length > 0 ? (
                <div className="space-y-2">
                  {dayAppointments.map((appointment) => (
                    <AppointmentCard key={appointment.id} appointment={appointment} />
                  ))}
                </div>
              ) : (
                <p className="text-sm text-muted-foreground">Nenhum agendamento para este dia.</p>
              )}
            </div>
          )
        })}
      </div>
    )
  }

  return (
    <div className="space-y-2">
      {filteredAppointments.length > 0 ? (
        filteredAppointments.map((appointment) => <AppointmentCard key={appointment.id} appointment={appointment} />)
      ) : (
        <p className="text-center py-8 text-muted-foreground">Nenhum agendamento encontrado para esta data.</p>
      )}
    </div>
  )
}

function AppointmentCard({ appointment }: { appointment: any }) {
  return (
    <Card className="p-4 hover:bg-accent transition-colors">
      <div className="flex items-center justify-between">
        <div>
          <p className="font-medium">{appointment.patientName}</p>
          <p className="text-sm text-muted-foreground">
            {appointment.time} - {appointment.type}
          </p>
        </div>
        <Badge variant={appointment.status === "Confirmado" ? "default" : "secondary"}>{appointment.status}</Badge>
      </div>
    </Card>
  )
}

