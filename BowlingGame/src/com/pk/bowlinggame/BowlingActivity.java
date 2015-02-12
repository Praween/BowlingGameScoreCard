package com.pk.bowlinggame;

import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class BowlingActivity extends Activity {

	private int frme[] = new int[10];
	private int allfrme[][] = new int[2][12];
	private int answer = 0;
	String s;

	Button btnNextRoll;
	Button btnTotalScore;
	List<String>Items;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bowling);
		btnNextRoll = (Button)findViewById(R.id.btn_nextRoll);
		btnTotalScore = (Button)findViewById(R.id.btn_totalScore);
		ScoreViewListAdapter adapter = new ScoreViewListAdapter(BowlingActivity.this, R.layout.scorecard_display, Items);
		ListView scoreListView = (ListView)findViewById(R.id.scoreListView);
		scoreListView.setAdapter(adapter);
		
		
		btnNextRoll.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				
				Random rn = new Random();
				answer = rn.nextInt(10) + 1;
				doBowlingAction();
			}
		});
		
		btnTotalScore.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				
				
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bowling, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		//int id = item.getItemId();
		/*if (id == R.id.action_settings) {
			return true;
		}*/
		return super.onOptionsItemSelected(item);
	}

	/*
	 * Actual Logic for implemetation over Strike and Spare.
	 */
	public void doBowlingAction() {

		do {
			for (int l1 = 0; l1 < 10; l1++)
				frme[l1] = 0;
			for (int l2 = 0; l2 < 12; l2++)
				allfrme[0][l2] = allfrme[1][l2] = 0;
			nxtfrme:

			for (int i = 0; i < 10; i++) {
				boolean chk = false;
				int Pins = 0;

				while (!chk) {

					System.out.printf("\n\t   FRAME %2d ",
							new Object[] { Integer.valueOf(i + 1) });

					System.out.printf("\n\t Ball 1: ");
					Pins = answer;
					// TODO: need to add click listener event
					if (Pins <= 10 && Pins >= 0) {
						allfrme[0][i] = Pins;
						chk = true;
					}
				}

				if (Pins == 10) {
					System.out.println("\n\t\t\tExcelent, Strike!!");
					continue;

				}
				chk = false;

				do {
					int Pins2 = 0;

					if (chk)
						continue nxtfrme;
					System.out.print("\n\t Ball 2: ");
					Pins2 = answer;

					if (Pins2 <= 10 && Pins2 >= 0 && Pins2 + allfrme[0][i] < 11) {
						allfrme[1][i] = Pins2;

						if (Pins2 + allfrme[0][i] == 10)
							System.out.println("\n\t\t\tNice, Spare!!");
						else if (allfrme[0][i] < 4 || Pins2 < 4)
							System.out.println("\n\t\t\tDo better next time");

						chk = true;
					}

				} while (true);

			}

			if (allfrme[0][9] == 10) {
				boolean chk1 = false;

				int Pins3 = 0;

				while (!chk1) {
					System.out.print("\n\t  XTR1\n\n\t Ball 1: ");
					Pins3 = answer;

					if (Pins3 < 11 && Pins3 >= 0) {
						allfrme[0][10] = Pins3;
						chk1 = true;
					}
				}

				if (allfrme[0][10] == 10) {
					boolean chk2 = false;

					int Pins4 = 0;

					System.out.println("\n\t\t\tExcelent, Strike...");

					if (allfrme[0][11] == 10)
						System.out.print("\n\t\t\tExcelent, Strike...");

					while (!chk2) {
						System.out.print("\n\t  XTR2\n\n\t Ball 2: ");
						Pins4 = answer;

						if (Pins4 < 11 && Pins4 > 0) {
							allfrme[0][11] = Pins4;
							chk2 = true;
						}
					}
				} else {
					boolean chk3 = false;

					int Pins5 = 0;

					while (!chk3) {
						System.out.print("\n\t  XTR2\n\n\t Ball 2: ");
						Pins5 = answer;

						if (Pins5 < 11 && Pins5 >= 0
								&& Pins5 + allfrme[0][10] < 11) {
							allfrme[1][10] = Pins5;
							chk3 = true;
						}
					}
				}
			} else if (allfrme[0][9] + allfrme[1][9] == 10) {
				boolean chk4 = false;

				int Pins6 = 0;

				while (!chk4) {
					System.out.print("\n\t  XTR2\n\n\t Ball 2: ");
					Pins6 = answer;

					if (Pins6 <= 10 && Pins6 >= 0) {
						allfrme[0][10] = Pins6;
						chk4 = true;
					}
				}
			}

			// start
			if (allfrme[0][0] + allfrme[0][1] == 10)
				frme[0] = 10 + allfrme[0][1];
			else
				frme[0] = allfrme[0][0] + allfrme[1][0];

			// middle
			if (allfrme[0][0] == 10) {
				if (allfrme[0][1] == 10)
					frme[0] = 20 + allfrme[0][2];
				else
					frme[0] = 10 + allfrme[0][1] + allfrme[1][1];
			}

			// end
			for (int j = 1; j < 10; j++) {

				if (allfrme[0][j] == 10) {
					if (allfrme[0][j + 1] == 10)
						frme[j] = frme[j - 1] + 20 + allfrme[0][j + 2];
					else
						frme[j] = frme[j - 1] + 10 + allfrme[0][j + 1]
								+ allfrme[1][j + 1];
					continue;
				}

				if (allfrme[0][j] + allfrme[1][j] == 10)
					frme[j] = frme[j - 1] + 10 + allfrme[0][j + 1];
				else
					frme[j] = frme[j - 1] + allfrme[0][j] + allfrme[1][j];

			}

			// Print
			System.out.print("\n\n\t  FRAME");
			for (int k = 1; k < 11; k++)
				System.out.printf("%4d", new Object[] { Integer.valueOf(k) });

			if (allfrme[0][9] == 10) {
				if (allfrme[0][10] == 10)
					System.out.print("  XTR1 XTR2");
				else
					System.out.print("  EXTRA");

			} else if (allfrme[0][9] + allfrme[1][9] == 10)
				System.out.print("  XTRA");
			System.out.print("\n\n\tBall 1 ");

			for (int l = 0; l < 10; l++)
				System.out.printf("%4d",
						new Object[] { Integer.valueOf(allfrme[0][l]) });

			if (allfrme[0][9] == 10) {
				if (allfrme[0][10] == 10)
					System.out.printf("%4d%4d",
							new Object[] { Integer.valueOf(allfrme[0][10]),
									Integer.valueOf(allfrme[0][11]) });
				else
					System.out.printf("%4d",
							new Object[] { Integer.valueOf(allfrme[0][10]) });

			} else if (allfrme[0][9] + allfrme[1][9] == 10)
				System.out.printf("%4d",
						new Object[] { Integer.valueOf(allfrme[0][10]) });
			System.out.print("\n\tBall 2 ");
			for (int i1 = 0; i1 < 10; i1++)
				System.out.printf("%4d",
						new Object[] { Integer.valueOf(allfrme[1][i1]) });

			if (allfrme[0][9] == 10 && allfrme[0][10] != 10)
				System.out.printf("%4d",
						new Object[] { Integer.valueOf(allfrme[1][10]) });

			System.out.print("\n\n\t  SCORE");
			for (int j1 = 0; j1 < 10; j1++)
				System.out.printf("%4d",
						new Object[] { Integer.valueOf(frme[j1]) });

			System.out.print("\n\n\t\t\tPlay more (Y/N)? ");

		} while (s.toUpperCase().charAt(0) != 'Y');
	}

	public class ScoreViewListAdapter extends BaseAdapter {

		List<String> items;
		int layoutResourceId;
		Context context;

		public ScoreViewListAdapter(Context context, int resource,
				List<String> items) {
			super();
			this.context = context;
			this.layoutResourceId = resource;
			this.items = items;

		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View vi = convertView;
			ViewHolder holder;
			if (vi == null) {
				holder = new ViewHolder();
				vi = getLayoutInflater().inflate(R.layout.scorecard_display, null, false);
				holder.Roll1 = (TextView) vi.findViewById(R.id.txt_strike);
				holder.Roll2 = (TextView) vi.findViewById(R.id.txt_spare);
				holder.FrameCount = (TextView) vi
						.findViewById(R.id.txt_frameCount);
				vi.setTag(holder);
			} else {
				holder = (ViewHolder) vi.getTag();
			}

			notifyDataSetChanged();
			return vi;
		}

		public final class ViewHolder {
			TextView Roll1;
			TextView Roll2;
			TextView FrameCount;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}
	}
}
