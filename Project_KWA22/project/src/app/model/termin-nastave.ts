import { IshodNastave } from "./ishod-nastave";
import { TipNastave } from "./tip-nastave";

export interface TerminNastave {
    id: number;
    vremePocetka: Date;
    vremeKraja: Date;
    ishodNastave: IshodNastave[];
    tipNastave: TipNastave[]
}
