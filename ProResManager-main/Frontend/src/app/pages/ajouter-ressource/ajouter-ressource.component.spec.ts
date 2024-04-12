import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AjouteRessourceComponent } from './ajouter-ressource.component';

describe('ProjetComponent', () => {
  let component: AjouteRessourceComponent;
  let fixture: ComponentFixture<AjouteRessourceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AjouteRessourceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AjouteRessourceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
