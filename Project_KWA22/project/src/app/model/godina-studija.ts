import { Predmet } from "./predmet";

export interface GodinaStudija {
    id:number;
    godina:Date;
    predmet:Predmet[]
}
