import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { AjouterProjetComponent } from './ajouter-projet.component';

describe('ProjetComponent', () => {
  let component: AjouterProjetComponent;
  let fixture: ComponentFixture<AjouterProjetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AjouterProjetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AjouterProjetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
