import { Pet } from "./Pet";

export class Adopter{
    adopterId:any;
    name: string | undefined
    phone: string | undefined
    email:string | undefined
    address: string | undefined
    birthDate: Date | undefined
    role: string | undefined
    shelterId: number | undefined
    Pets!: Pet[]
    RequestedPets!:Pet[]

    constructor(){        
    }
    

}