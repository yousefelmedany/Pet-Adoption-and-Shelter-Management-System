export class Staff {
    staffId!:number
    name: string
    phone: string
    email:string
    address: string
    birthDate: Date
    role: string
    shelter: any
    staff!:any
    constructor(name: string, email: string, phone: string, address: string,
        birthDate: Date, role: string, shelterId: any) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.birthDate = birthDate;
        this.role = role;
        this.shelter = shelterId;
    }
}