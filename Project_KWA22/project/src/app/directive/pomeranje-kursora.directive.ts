import { Directive, ElementRef, HostListener, Input } from '@angular/core';

@Directive({
  selector: '[appPomeranjeKursora]'
})
export class PomeranjeKursoraDirective {
  @Input()
  appPomeranjeKursora = '';
  
  constructor(private element : ElementRef) { }

  @HostListener('mouseenter') onMouseEnter() {
    this.pregledMisa('lightgreen');
  }

  @HostListener('mouseleave') onMouseLeave() {
    this.pregledMisa('');
  }

  private pregledMisa(color: string) {
    this.element.nativeElement.style.backgroundColor = color;
  }

}
