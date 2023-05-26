    package org.example.listeners;

    import com.fasterxml.jackson.core.JsonProcessingException;
    import net.dv8tion.jda.api.entities.Message;
    import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
    import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
    import net.dv8tion.jda.api.hooks.ListenerAdapter;

    import org.example.prayertimes.PrayerTimes;
    import org.example.prayertimes.Timings;
    import org.example.quran.AyahCommand;
    import org.example.quran.SurahCommand;

    import java.io.IOException;

    public class MyListener extends ListenerAdapter {
        @Override
        public void onMessageReceived(MessageReceivedEvent event) {
            if (event.getAuthor().isBot()) {
                return;
            }

            Message message = event.getMessage();
            String content = message.getContentRaw();

            MessageChannel channel = event.getChannel();

            switch (content) {

                case "asalamaleikum":
                    channel.sendMessage("Wasalamaleikum").queue();
                    break;
                default:
                    if (content.contains(">>surah")) {
                        SurahCommand surahCommand = new SurahCommand();
                        String surahResponse = null;
                        try {
                            surahResponse = surahCommand.surahGet(content);
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        channel.sendMessage(surahResponse).queue();
                    } else if (content.contains(">>ayah")) {
                        AyahCommand ayahCommand = new AyahCommand();
                        String ayahResponse = null;
                        try {
                            ayahResponse = ayahCommand.getEnglishAyah(content);
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                        channel.sendMessage(ayahResponse).queue();
                    } else if (content.contains(">>aayah")) {
                        AyahCommand ayahCommand = new AyahCommand();
                        String ayahResponse = null;
                        try {
                            ayahResponse = ayahCommand.getArabicAyah(content);
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                        channel.sendMessage(ayahResponse).queue();
                    } else if (content.contains(">>prayertimes")) {
                        PrayerTimes prayerTimes = new PrayerTimes();
                        Timings timings=new Timings();
                        try {
                            timings = prayerTimes.prayerTimeGet(content);
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                        channel.sendMessage(timings.toString()).queue();

                    }
            }
        }
    }