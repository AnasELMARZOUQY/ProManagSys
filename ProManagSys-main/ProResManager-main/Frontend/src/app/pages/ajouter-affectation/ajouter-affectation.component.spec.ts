import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AjouterAffectationComponent } from './ajouter-affectation.component';

describe('AjouterAffectationComponent', () => {
  let component: AjouterAffectationComponent;
  let fixture: ComponentFixture<AjouterAffectationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AjouterAffectationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AjouterAffectationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
