export class Staff {
    name: string
    phone: string
    email:string
    address: string
    birthDate: Date
    role: string
    shelterId: number
    constructor(name: string, email: string, phone: string, address: string,
        birthDate: Date, role: string, shelterId: number) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.birthDate = birthDate;
        this.role = role;
        this.shelterId = shelterId;
    }
}