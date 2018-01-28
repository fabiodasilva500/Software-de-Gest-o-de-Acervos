package View;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import Entidades.TCC;

public class TabelaTCCModel extends AbstractTableModel{

	private List<TCC>tcc;
	private String []nomes={"ID", "Título", "Autor","Instit.", "Ano", "Semestre", "Formato", "Curso", "Descr."};

	public TabelaTCCModel(List<TCC>tcc){
		this.tcc=tcc;
	}

	public boolean isCellEditable(int linha, int coluna){
		return false;
	}

	@Override
	public int getColumnCount() {
		return nomes.length;
	}

	public String getColumnName(int i){
		return nomes[i];
	}

	@Override
	public int getRowCount() {
		return tcc.size();
	}



	@Override
	public Object getValueAt(int linha, int coluna) {
		Object o=" ";

		if((coluna<=getColumnCount())){
			TCC t=tcc.get(linha);

			if(coluna==0){
				o=t.getID();
			}
			else
				if(coluna==1){
					o=t.getTitulo();
				}
				else
					if(coluna==2){
						o=t.getDesenvolvedor();
					}
					else
						if(coluna==3){
							o=t.getInstituicao();
						}
						else
							if(coluna==4){
								o=t.getAno();
							}
							else
								if(coluna==5){
									o=t.getSemestre();
								}
								else
									if(coluna==6){
										o=t.getFormato();
									}
									else
										if(coluna==7){
											o=t.getCurso();
										}
										else
											if(coluna==8){
												o=t.getDescricao();
											}
		}
		return o;
	}



	public Class<?> getColumnClass(int indiceColuna){
		if(indiceColuna==0){
			return Integer.class;
		}
		else
			if(indiceColuna==1){
				return String.class;
			}
			else
				if(indiceColuna==2){
					return String.class;
				}
				else
					if(indiceColuna==3){
						return String.class;
					}
					else
						if(indiceColuna==4){
							return Integer.class;
						}
						else
							if(indiceColuna==5){
								return String.class;
							}
							else{
								if(indiceColuna==6){
									return String.class;
								}
								else
									if(indiceColuna==7){
										return String.class;
									}
									else
										return String.class;
							}
	}


	public TCC get(int row){
		return tcc.get(row);
	}

}
