import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar"
import { Badge } from "@/components/ui/badge"

const appointments = [
  {
    id: "1",
    patient: {
      name: "Ana Silva",
      avatar: "/placeholder.svg?height=32&width=32",
      initials: "AS",
    },
    time: "09:00",
    type: "Consulta Inicial",
    status: "Confirmado",
  },
  {
    id: "2",
    patient: {
      name: "Carlos Oliveira",
      avatar: "/placeholder.svg?height=32&width=32",
      initials: "CO",
    },
    time: "10:30",
    type: "Retorno",
    status: "Pendente",
  },
  {
    id: "3",
    patient: {
      name: "Mariana Santos",
      avatar: "/placeholder.svg?height=32&width=32",
      initials: "MS",
    },
    time: "14:00",
    type: "Exame",
    status: "Confirmado",
  },
  {
    id: "4",
    patient: {
      name: "Pedro Costa",
      avatar: "/placeholder.svg?height=32&width=32",
      initials: "PC",
    },
    time: "15:30",
    type: "Procedimento",
    status: "Confirmado",
  },
  {
    id: "5",
    patient: {
      name: "Juliana Lima",
      avatar: "/placeholder.svg?height=32&width=32",
      initials: "JL",
    },
    time: "17:00",
    type: "Retorno",
    status: "Pendente",
  },
]

export function RecentAppointments() {
  return (
    <div className="space-y-8">
      {appointments.map((appointment) => (
        <div key={appointment.id} className="flex items-center">
          <Avatar className="h-9 w-9">
            <AvatarImage src={appointment.patient.avatar} alt={appointment.patient.name} />
            <AvatarFallback>{appointment.patient.initials}</AvatarFallback>
          </Avatar>
          <div className="ml-4 space-y-1">
            <p className="text-sm font-medium leading-none">{appointment.patient.name}</p>
            <p className="text-sm text-muted-foreground">
              {appointment.time} - {appointment.type}
            </p>
          </div>
          <div className="ml-auto">
            <Badge variant={appointment.status === "Confirmado" ? "default" : "secondary"}>{appointment.status}</Badge>
          </div>
        </div>
      ))}
    </div>
  )
}

