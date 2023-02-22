import { IshodNastave } from "./ishod-nastave";
import { TipNastave } from "./tip-nastave";

export interface TerminNastavePage<TerminNastave> {
    content: TerminNastave[];
  }
export interface TerminNastave {
    id: number;
    vremePocetka: Date;
    vremeKraja: Date;
    ishodNastave: IshodNastave[];
    tipNastave: TipNastave[]
}
